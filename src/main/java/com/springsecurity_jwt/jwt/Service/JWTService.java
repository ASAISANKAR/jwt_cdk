package com.springsecurity_jwt.jwt.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

  @Value("${jwt.secretkey}")
  private String SECRET_KEY;

  public String generateToken(String username){
    byte[] keyBytes = SECRET_KEY.getBytes();
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10hrs
        .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
        .compact();
  }

}
