package com.staxter.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * The type User.
 */
@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(nullable = false)
  private String firstName;

  private String lastName;

  @Column(unique = true, nullable = false)
  private String userName;

  @Transient
  @JsonProperty(access = Access.WRITE_ONLY)
  private String plainTextPassword;

  @JsonIgnore
  @Column(nullable = false)
  @Accessors(chain = true)
  private String hashedPassword;

  /**
   * Instantiates a new User.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param userName the user name
   * @param plainTextPassword the plain text password
   */
  public User(String firstName, String lastName, String userName, String plainTextPassword) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.plainTextPassword = plainTextPassword;
  }

}
