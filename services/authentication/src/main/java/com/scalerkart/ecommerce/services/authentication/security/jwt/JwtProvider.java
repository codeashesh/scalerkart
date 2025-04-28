package com.scalerkart.ecommerce.services.authentication.security.jwt;

import com.scalerkart.ecommerce.services.authentication.security.userprinciple.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;
    @Value("${jwt.refreshExpiration}")
    private int jwtRefreshExpiration;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        List<String> authorities = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(userPrinciple.getUsername())
                .claim("authorities", authorities)
                .claim("userId", userPrinciple.id())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration * 1000L))
                .signWith(generateHS512KeyFromString())
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .subject(userPrinciple.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtRefreshExpiration * 1000L))
                .signWith(generateHS512KeyFromString())
                .compact();
    }

    public String reduceTokenExpiration(String token) {
        // Decode the token to extract its claims
        token = token.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .verifyWith(generateHS512KeyFromString())
                .build()
                .parseClaimsJws(token)
                .getPayload();

        // Build a new token with the updated expiration time
        return Jwts.builder()
                .claims(claims)
                .signWith(generateHS512KeyFromString())
                .expiration(new Date(0))
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(generateHS512KeyFromString())
                    .build()
                    .parseClaimsJws(token);
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

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(generateHS512KeyFromString())
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public SecretKey generateHS512KeyFromString() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
