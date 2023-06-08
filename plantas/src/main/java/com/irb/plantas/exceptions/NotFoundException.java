package com.irb.plantas.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
  public NotFoundException() {
    super(HttpStatus.NOT_FOUND.value(), (String)null, (Throwable)null);
  }

  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), message, (Throwable)null);
  }

  public NotFoundException(String message, Throwable cause) {
    super(HttpStatus.NOT_FOUND.value(), message, cause);
  }

  public NotFoundException(String message, String body, Throwable cause) {
    super(HttpStatus.NOT_FOUND.value(), message, body, cause);
  }
}