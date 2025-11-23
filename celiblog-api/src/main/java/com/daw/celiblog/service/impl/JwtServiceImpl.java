package com.daw.celiblog.service.impl;

import com.daw.celiblog.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

   /* private final Key key;
    private final long expirationMs;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret,
                          @Value("${jwt.expiration}") Long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // clave segura
        this.expirationMs = expirationMs;
    }

    @Override
    public String generateToken(String username) {
        String us = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return us;
    }

    @Override
    public String extractUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // usamos la misma Key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }*/
}
