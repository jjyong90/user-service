package com.staxter.userservice.exception;

import org.springframework.http.HttpStatus;

/**
 * The type User Already Exists exception.
 */
public class UserAlreadyExistsException extends AbstractRuntimeException {

  /**
   * Instantiates a new  User Already Exists exception.
   */
  public UserAlreadyExistsException() {
    super(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS", "A user with the given username already exists", null);
  }

}
