package com.questify.questify.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String SECRET_KEY = "questify";

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    String path = request.getRequestURI();
    if (path.startsWith("/questify/login") || path.startsWith("/questify/signup")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);

      try {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(jwt)
            .getBody();

        String username = claims.getSubject();
        // You can optionally set the authenticated user in the context
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            username, null, List.of()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid or expired token");
        return;
      }
    } else {
      // No token in header
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Missing Authorization header");
      return;
    }

    filterChain.doFilter(request, response);
  }
}
