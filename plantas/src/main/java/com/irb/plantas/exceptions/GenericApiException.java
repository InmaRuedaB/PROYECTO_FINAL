package com.irb.plantas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class GenericApiException extends RuntimeException {

  private final int code;
  private final String description;
  private final Throwable cause;

}
