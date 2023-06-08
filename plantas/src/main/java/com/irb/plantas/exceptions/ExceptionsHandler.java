package com.irb.plantas.exceptions;


import java.util.Map;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@SuppressWarnings("checkstyle:JavadocMethod")
public class ExceptionsHandler {

  private static final String MESSAGE = "message";

  @SuppressWarnings("PMD.ConfusingTernary")
  @ExceptionHandler({GenericApiException.class})
  public ResponseEntity<Map<String, String>> genericApiException(GenericApiException ex) {
    return ResponseEntity
        .status(ex.getCode())
        .contentType(MediaType.APPLICATION_JSON)
        .body(ex.getCode() != 204 ? Map.of("Error", ex.getDescription()) : null);
  }

  @ExceptionHandler({BadRequestException.class})
  public ResponseEntity<Map<String, String>> badRequestException(BadRequestException ex) {
    return ResponseEntity
        .status(ex.getCode())
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of(MESSAGE, ex.getMessage()));
  }

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<Map<String, String>> noSuchElementException(NoSuchElementException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND.value())
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of(MESSAGE, ex.getMessage()));
  }


  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Map<String, String>> notFoundException(NotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND.value())
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of(MESSAGE, ex.getMessage()));
  }

  @ExceptionHandler(value = {UnauthorizedException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Map<String, String>> unauthorized(final UnauthorizedException ex) {
    log.warn("Unauthorized, returning 401");
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED.value())
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of(MESSAGE, ex.getMessage()));
  }

  @ExceptionHandler(value = {ForbiddenException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseEntity<Map<String, String>> forbidden(final ForbiddenException ex) {
    log.warn("Unauthorized, returning 403");
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN.value())
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of(MESSAGE, ex.getMessage()));
  }

}
