package com.staxter.userservice.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.staxter.userservice.exception.UserAlreadyExistsException;
import com.staxter.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  UserRepository repository;

  @Test
  void createUser_throwsUserAlreadyExists() {
    User user = new User("ajasja", null, "zzz", "111");
    user.setHashedPassword("aaa");

    repository.createUser(user);

    assertThrows(UserAlreadyExistsException.class, () -> repository.createUser(user));
  }

  @Test
  void createUser_returnsSavedUser() {
    User user = new User("ajasja", null, "zzz", "111");
    user.setHashedPassword("aaa");

    assertNull(user.getId());

    User result = repository.createUser(user);

    assertNotNull(result.getId());
  }


}
