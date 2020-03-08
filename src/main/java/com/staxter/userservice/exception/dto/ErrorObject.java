package com.staxter.userservice.exception.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * The type Error object.
 */
@Data
public class ErrorObject implements Serializable {

  private String code;
  private String description;

}
