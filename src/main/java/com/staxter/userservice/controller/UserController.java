package com.staxter.userservice.controller;

import com.staxter.userservice.model.User;
import com.staxter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/userservice/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;

  /**
   * Register user user.
   *
   * @param user the user
   * @return the user
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User registerUser(@RequestBody User user) {

    return userService.createUser(user);
  }

}
