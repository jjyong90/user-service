package com.staxter.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.staxter.userservice.model.User;
import com.staxter.userservice.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {

  private static final String VALID_USER_JSON = "{\"firstName\":\"asasa\",\"userName\":\"asasa\",\"plainTextPassword\":\"a\"}";
  private static final User USER = new User("fasas", "zzzsdsd", "zzz", "111");

  @MockBean
  private UserService service;

  @Autowired
  private MockMvc mvc;

  @Test
  @SneakyThrows
  void registerUser_callsService() {
    when(service.createUser(any())).thenAnswer(x -> x.getArgument(0));

    registerUser(VALID_USER_JSON);

    verify(service).createUser(any(User.class));
  }

  @SneakyThrows
  private ResultActions registerUser(String user) {
    return mvc.perform(
        post("/userservice/register")
            .with(csrf())
            .with(user("a"))
            .content(user)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @SneakyThrows
  void registerUser_returnsCreated_whenUserIsValid() {
    when(service.createUser(any())).thenAnswer(x -> x.getArgument(0));

    registerUser(VALID_USER_JSON)
        .andExpect(status().isCreated());
  }

  @Test
  @SneakyThrows
  void registerUser_returnsId() {
    mockCreateUser();
    USER.setId("asasasa");

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.id").value(USER.getId()));
  }

  private void mockCreateUser() {
    when(service.createUser(any())).thenReturn(USER);
  }

  @Test
  @SneakyThrows
  void registerUser_returnsFirstName() {
    mockCreateUser();

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.firstName").value(USER.getFirstName()));
  }

  @Test
  @SneakyThrows
  void registerUser_returnsLastName() {
    mockCreateUser();

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.lastName").value(USER.getLastName()));
  }

  @Test
  @SneakyThrows
  void registerUser_returnsUserName() {
    mockCreateUser();

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.userName").value(USER.getUserName()));
  }

  @Test
  @SneakyThrows
  void registerUser_dontReturnPlainPassword() {
    mockCreateUser();

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.plainTextPassword").doesNotExist());
  }

  @Test
  @SneakyThrows
  void registerUser_dontReturnHashedPassword() {
    mockCreateUser();

    registerUser(VALID_USER_JSON)
        .andExpect(jsonPath("$.hashedPassword").doesNotExist());
  }

  @Test
  @SneakyThrows
  void registerUser_returnsBadRequest_whenEmptyFirstName() {
    String userNoFirstName = "{\"userName\":\"asasa\",\"plainTextPassword\":\"a\"}";
    registerUser(userNoFirstName)
        .andExpect(status().isBadRequest());
  }

  @Test
  @SneakyThrows
  void registerUser_returnsBadRequest_whenEmptyUserName() {
    String userNoUserName = "{\"firstName\":\"asasa\",\"plainTextPassword\":\"a\"}";
    registerUser(userNoUserName)
        .andExpect(status().isBadRequest());
  }

  @Test
  @SneakyThrows
  void registerUser_returnsBadRequest_whenEmptyPassword() {
    String userNoPassword = "{\"firstName\":\"asasa\",\"userName\":\"asasa\"}";
    registerUser(userNoPassword)
        .andExpect(status().isBadRequest());
  }

}
