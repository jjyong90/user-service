package com.staxter.userservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * The type AbstractRuntimeException.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractRuntimeException extends RuntimeException {

  /**
   * The Status.
   */
  protected final HttpStatus status;
  /**
   * The Code.
   */
  protected final String code;
  /**
   * The Message.
   */
  protected final String message;
  /**
   * The Exception.
   */
  protected final Throwable exception;

  /**
   * Instantiates a new Abstract runtime exception.
   *
   * @param status the status
   * @param code the code
   * @param message the message
   * @param exception the exception
   */
  protected AbstractRuntimeException(HttpStatus status, String code, String message, Throwable exception) {
    super(message, exception);
    this.status = status;
    this.code = code;
    this.message = message;
    this.exception = exception;
  }

}
