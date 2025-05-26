package com.questify.questify.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

  private static final String SECRET_KEY = "questify";
  private static final String REFRESH_SECRET = "questify-refresh";

  public static String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuer("questify")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public static String generateRefreshToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 7 days
        .signWith(SignatureAlgorithm.HS256, REFRESH_SECRET)
        .compact();
  }

}
