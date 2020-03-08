package com.staxter.userservice.service;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import com.staxter.userservice.model.User;
import com.staxter.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceImplTest {

  @Mock
  private UserRepository repository;

  private UserService service;

  @BeforeEach
  void setUp() {
    initMocks(this);
    service = new UserServiceImpl(repository);
  }

  @Test
  void createUser_callsRepository() {
    User user = new User("asasas", "aaa", "aa", "zzx");

    service.createUser(user);

    verify(repository).createUser(user);
  }

}
