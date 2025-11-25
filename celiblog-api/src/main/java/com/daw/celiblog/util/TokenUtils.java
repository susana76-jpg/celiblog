package com.daw.celiblog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET ="a31afad321fd32f1a3df156a14f51d6a5f";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000l;

    public static String createToken(String nombre, String email, String id, String rol){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("email", email);
        extra.put("id", id);
        extra.put("rol", rol);

        return Jwts.builder()
                .setSubject(email)
                .setId(id)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build().parseClaimsJws(token).getBody();

            String email = claims.getSubject();
            String id = claims.getId();

            return new UsernamePasswordAuthenticationToken(email, id, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }

    }

}
