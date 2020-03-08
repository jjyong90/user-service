package com.staxter.userservice.config;

import com.staxter.userservice.model.User;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The type User validator.
 */
public class UserValidator implements Validator {

  @Override
  public boolean supports(@NonNull Class<?> clazz) {
    return true;
  }

  @Override
  public void validate(@NonNull  Object target, @NonNull Errors errors) {
    if (target instanceof User) {
      User user = (User) target;
      if (StringUtils.isEmpty(user.getFirstName())) {
        errors.rejectValue("firstName", "400", "The field 'firstName' can't be null");
      }
      if (StringUtils.isEmpty(user.getUserName())) {
        errors.rejectValue("userName", "400", "The field 'userName' can't be null");
      }
      if (StringUtils.isEmpty(user.getPlainTextPassword())) {
        errors.rejectValue("plainTextPassword", "400", "The field 'plainTextPassword' can't be null");
      }
    }
  }

}
