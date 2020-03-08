package com.staxter.userservice.exception.handler;

import com.staxter.userservice.exception.AbstractRuntimeException;
import com.staxter.userservice.exception.dto.ErrorObject;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
    return new ResponseEntity<>(new ErrorObject(exception.getCode(), exception.getMessage()), exception.getStatus());
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

    return new ResponseEntity<>(new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), throwable.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle validation exceptions.
   *
   * @param ex the MethodArgumentNotValidException
   * @param headers the http headers
   * @param status the http status
   * @param request the web request
   * @return the response entity
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    log.error(ex);

    return ResponseEntity
        .badRequest()
        .body(new ErrorObject(HttpStatus.BAD_REQUEST.name(), getErrorsAsDescription(ex)));
  }

  private String getErrorsAsDescription(MethodArgumentNotValidException ex) {
    return ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining("\n"));
  }

}
