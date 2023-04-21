package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;

public class JwtAuthenticationException extends AbstractAnteikuException {

  public JwtAuthenticationException(Map<String, String> errors, String message) {
    super(errors, message);
  }
}
