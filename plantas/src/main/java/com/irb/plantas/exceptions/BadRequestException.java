package com.irb.plantas.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {
  public static final String DEFAULT_BAD_REQUEST_MSG = "Could not process request";

  public BadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST.value(), message, (Throwable)null);
  }

  public BadRequestException(String message, Throwable cause) {
    super(HttpStatus.BAD_REQUEST.value(), message, cause);
  }

  public BadRequestException(String message, String body, Throwable cause) {
    super(HttpStatus.BAD_REQUEST.value(), message, body, cause);
  }

  public BadRequestException() {
    super(HttpStatus.BAD_REQUEST.value(), "Could not process request", (Throwable)null);
  }
}
