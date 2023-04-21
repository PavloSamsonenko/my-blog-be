package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;

public class IncorrectCredentialsException extends AbstractAnteikuException {

  public IncorrectCredentialsException(Map<String, String> errors, String message) {
    super(errors, message);
  }
}
