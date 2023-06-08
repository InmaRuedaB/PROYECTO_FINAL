package com.irb.plantas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
public class Jwtutils {

  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Value("${application.security.jwt.base64Secret}")
  private String base64Secret;

  @Value("${application.security.jwt.expiration-seconds}")
  private String jwtExpirationSeconds;

  public String generateJwtToken(Authentication authentication) {
    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

    Claims claims = Jwts.claims();
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    claims.put("authorities", authorities);

    Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));

    return Jwts.builder().setClaims(claims)
        .setSubject(userPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + Integer.parseInt(jwtExpirationSeconds) * 1000L))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(Decoders.BASE64.decode(base64Secret))
        .build().parseClaimsJws(extractToken(token)).getBody();
  }

  public String getUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(Decoders.BASE64.decode(base64Secret)).build().parseClaimsJws(authToken);
      return true;
    } catch (SecurityException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
      throw e;
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
      throw e;
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
      throw e;
    }  catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
      throw e;
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
      throw e;
    }

  }

  public String extractToken(String token) {
    if(StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
      return token.substring(TOKEN_PREFIX.length());
    }
    return  null;
  }

  public String extractToken(HttpServletRequest request) {
    return this.extractToken(request.getHeader(AUTHORIZATION_HEADER));
  }
}
