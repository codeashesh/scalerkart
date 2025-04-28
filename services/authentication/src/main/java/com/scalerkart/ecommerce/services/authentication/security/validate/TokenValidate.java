package com.scalerkart.ecommerce.services.authentication.security.validate;

import com.scalerkart.ecommerce.services.authentication.exception.wrapper.UserNotAuthenticatedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class TokenValidate {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public boolean validateToken(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (SECRET_KEY == null || SECRET_KEY.isEmpty())
                throw new IllegalArgumentException("Not found secret key in structure");

            if (token.startsWith("Bearer "))
                token = token.replace("Bearer ", "");

            try {
                Claims claims = Jwts
                        .parser()
                        .verifyWith(generateHS512KeyFromString())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                long currentTimeMillis = System.currentTimeMillis();
                return claims.getExpiration().getTime() >= currentTimeMillis;
            } catch (ExpiredJwtException ex) {
                throw new IllegalArgumentException("Token has expired.");
            } catch (MalformedJwtException ex) {
                throw new IllegalArgumentException("Invalid token.");
            } catch (SignatureException ex) {
                throw new IllegalArgumentException("Token validation error.");
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Token validation error: " + ex.getMessage());
            }
        } else {
            throw new UserNotAuthenticatedException("Invalid token");
        }

    }

    public SecretKey generateHS512KeyFromString() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

}
