package com.scalerkart.ecommerce.services.cart.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateHS512KeyFromString())
                .build()
                .parseClaimsJws(token)
                .getPayload();
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, extractAuthorities(claims));
    }

    public Long getUserIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateHS512KeyFromString())
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("userId", Long.class);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(generateHS512KeyFromString())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid format Token -> Message: ", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT Token -> Message: ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT Token -> Message: ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: ", e);
        }
        return false;
    }

    public SecretKey generateHS512KeyFromString() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private List<GrantedAuthority> extractAuthorities(Claims claims) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        final List<String> roles = (List<String>) claims.get("authorities");
        if (roles != null) {
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
        }
        return authorities;
    }

}
