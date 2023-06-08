package com.irb.plantas.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ForbiddenException extends HttpException {

  private static final long serialVersionUID = 1L;

  public ForbiddenException() {
    super(HttpStatus.FORBIDDEN.value(), (String) null, (Throwable) null);
  }

  public ForbiddenException(HttpClientErrorException clientErrorEx) {
    super(HttpStatus.FORBIDDEN.value(), (String) null, clientErrorEx);
  }

  public ForbiddenException(String message, HttpHeaders headers) {
    super(HttpStatus.FORBIDDEN.value(), message, (String) null, headers, (Throwable) null);
  }

  public ForbiddenException(String message) {
    super(HttpStatus.FORBIDDEN.value(), message, (Throwable) null);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(HttpStatus.FORBIDDEN.value(), message, (String) null, cause);
  }

  public ForbiddenException(String message, Throwable cause, HttpHeaders headers) {
    super(HttpStatus.FORBIDDEN.value(), message, (String) null, headers, cause);
  }

  public ForbiddenException(String message, String body, Throwable cause, HttpHeaders headers) {
    super(HttpStatus.FORBIDDEN.value(), message, body, headers, cause);
  }
}