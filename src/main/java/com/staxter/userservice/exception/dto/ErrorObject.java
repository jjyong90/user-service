package com.staxter.userservice.exception.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Error object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject implements Serializable {

  private String code;
  private String description;

}
