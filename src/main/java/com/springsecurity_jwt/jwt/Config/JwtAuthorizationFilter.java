package com.springsecurity_jwt.jwt.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private String SECRET_KEY="a-string-secret-at-least-256-bits-long";
  private static String Authorization = "Authorization";
  private static String Bearer = "Bearer ";


  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
      throws ServletException, IOException {
       SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(
        StandardCharsets.UTF_8));
        String authHeader = request.getHeader(Authorization);
        if(authHeader!=null && authHeader.startsWith(Bearer))
        {
          try {
            String jwt = authHeader.substring(7).trim();
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);
            List<SimpleGrantedAuthority> authorities =
                roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();


            UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
          }
          catch (Exception e)
          {
            System.out.println("Invalid JWT: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(
                "{\"error\": \"Invalid JWT\", \"message\": \"" + e.getMessage() + "\"}"
            );
            return;
          }
        }
        filterChain.doFilter(request,response);
  }
}
