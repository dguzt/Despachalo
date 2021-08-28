package org.guzman.despachalo.adapter.web.config.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final Key secretKey;

    public JwtService(
            @Value("${despachalo.security.jwt-key}") String jwtKey) {
        this.secretKey = secretKey(jwtKey);
    }

    public String generateToken(UserDetails userDetails) {
        var claims = Map.<String, Object>of();
        return createToken(claims, userDetails.getUsername());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Key secretKey(String jwtKey) {
        return Keys.hmacShaKeyFor(jwtKey.getBytes());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        var time = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final var claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
