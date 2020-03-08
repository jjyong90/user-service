package com.staxter.userservice.repository;

import com.staxter.userservice.exception.UserAlreadyExistsException;
import com.staxter.userservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

  /**
   * Exists by user name boolean.
   *
   * @param userName the user name
   * @return the boolean
   */
  boolean existsByUserName(String userName);

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */
  default User createUser(User user) throws UserAlreadyExistsException {
    if (existsByUserName(user.getUserName())) {
      throw new UserAlreadyExistsException();
    }
    return save(user);
  }

}
