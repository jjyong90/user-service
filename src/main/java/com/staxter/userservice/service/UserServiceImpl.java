package com.staxter.userservice.service;

import com.staxter.userservice.model.User;
import com.staxter.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;

  @Override
  public User createUser(User user) {
    return repository.createUser(
        user.setHashedPassword(
            encoder.encode(user.getPlainTextPassword())));
  }

}
