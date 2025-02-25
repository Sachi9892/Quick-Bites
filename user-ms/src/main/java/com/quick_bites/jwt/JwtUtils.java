package com.quick_bites.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days


    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes()); // Convert the secret string to a SecretKey
    }


    // Generate a JWT
    public String generateToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId) // Store userId as a custom claim
                .setIssuedAt(new Date()) // Set the issued-at time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
                .signWith(getSecretKey()) // Sign the token with the secret key
                .compact(); // Convert to a compact string
    }


    // Extract claims from the JWT
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey()) // Verify the token using the secret key
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody(); // Get the payload (claims)
    }


    public Long extractUserId(String token) {
        Claims claims = extractClaims(token);
        return claims.get("userId", Long.class); // Extract userId as a Long
    }

    // Validate the JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey()) // Verify the token using the secret key
                    .build()
                    .parseClaimsJws(token); // Parse the token
            return true; // Token is valid
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }

}
