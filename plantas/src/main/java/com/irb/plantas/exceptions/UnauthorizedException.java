package com.irb.plantas.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED.value(), (String)null, (Throwable)null);
  }

  public UnauthorizedException(String message) {
    super(HttpStatus.UNAUTHORIZED.value(), message, (Throwable)null);
  }

  public UnauthorizedException(Throwable cause) {
    super(HttpStatus.UNAUTHORIZED.value(), (String)null, cause);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(HttpStatus.UNAUTHORIZED.value(), message, (String)null, cause);
  }

  public UnauthorizedException(String message, String body, Throwable cause) {
    super(HttpStatus.UNAUTHORIZED.value(), message, body, cause);
  }
}
