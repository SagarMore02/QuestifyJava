package com.questify.questify.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtValidator {

  private static final String SECRET_KEY = "questify";

  public static Claims validateToken(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }
}

