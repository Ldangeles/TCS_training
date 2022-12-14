package com.Coffee.CoffeeNetwork.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private long ttl;
    private Key key;

    public JWTService(@Value("${jwt.ttl}") long ttl, @Value("${jwt.key}") String signKey){
        this.ttl=ttl;
        this.key=Keys.hmacShaKeyFor(signKey.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + ttl*1000))
        .signWith(key).compact();
    }

    public String getUsername(String token){
        return Jwts.parserBuilder()
        .setSigningKey(key).build()
        .parseClaimsJws(token)
        .getBody().getSubject();
    }

}
