package com.scalerkart.ecommerce.services.authentication.security.validate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class AuthorityTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public List<String> checkPermission(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(generateHS512KeyFromString())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("authorities", List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public SecretKey generateHS512KeyFromString() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
