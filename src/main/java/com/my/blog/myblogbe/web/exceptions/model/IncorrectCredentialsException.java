package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;
import org.springframework.http.HttpStatus;

public class IncorrectCredentialsException extends AbstractAnteikuException {

  public IncorrectCredentialsException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
