package com.currency.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class WrongParameterException extends RuntimeException {
  private final HttpStatus status;

  public WrongParameterException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
