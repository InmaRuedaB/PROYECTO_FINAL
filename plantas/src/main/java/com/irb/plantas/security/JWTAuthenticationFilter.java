package com.irb.plantas.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irb.plantas.model.LoginReq;
import com.irb.plantas.model.LoginRes;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public static String TOKEN_TYPE = "Bearer";

  private Jwtutils jwtutils;

  private ObjectMapper objectMapper;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Jwtutils jwtutils, ObjectMapper objectMapper, String loginEndpoint) {
    this.setAuthenticationManager(authenticationManager);
    this.jwtutils = jwtutils;
    this.objectMapper = objectMapper;
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginEndpoint, "POST"));
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    if(!"POST".equals(request.getMethod())) {
      throw new AuthenticationServiceException("Método de autenticación no soportado: " + request.getMethod());
    }
    String username = null;
    String password = null;

    try {
      LoginReq loginReq = this.objectMapper.readValue(request.getInputStream(), LoginReq.class);
      username = loginReq.getUsername().trim();
      password = loginReq.getPassword().trim();
    }catch (Exception e) {
      username = null;
      password = null;
    }

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String token = jwtutils.generateJwtToken(authResult);
    LoginRes loginRes = LoginRes.builder().accessToken(token).tokenType(TOKEN_TYPE).username(authResult.getName()).build();

    response.getWriter().write(objectMapper.writeValueAsString(loginRes));
    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON.toString());
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    logger.info("Error de autenticación: "+failed.getMessage());
    response.setStatus(401);
    response.setContentType(MediaType.APPLICATION_JSON.toString());
  }
}
