package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;

public class UserNotActivatedException extends AbstractAnteikuException {

  public UserNotActivatedException(Map<String, String> errors, String message) {
    super(errors, message);
  }
}
