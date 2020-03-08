package com.staxter.userservice.service;

import com.staxter.userservice.model.User;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */
  User createUser(User user);

}
