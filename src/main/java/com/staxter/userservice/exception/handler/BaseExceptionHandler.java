package com.staxter.userservice.exception.handler;

import com.staxter.userservice.exception.AbstractRuntimeException;
import com.staxter.userservice.exception.dto.ErrorObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Base exception handler.
 */
@Log4j2
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(AbstractRuntimeException.class)
  public ResponseEntity<ErrorObject> handleException(AbstractRuntimeException exception) {
    log.error(exception);
    return new ResponseEntity<>(buildErrorObject(exception), exception.getStatus());
  }

  private ErrorObject buildErrorObject(AbstractRuntimeException exception) {
    ErrorObject error = new ErrorObject();
    error.setCode(exception.getCode());
    error.setDescription(exception.getMessage());
    return error;
  }

  /**
   * Handle throwable response entity.
   *
   * @param throwable the throwable
   * @return the response entity
   */
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorObject> handleThrowable(Throwable throwable) {
    log.error(throwable);

    ErrorObject error = new ErrorObject();
    error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
    error.setDescription(throwable.getMessage());

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
