package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;
import org.springframework.http.HttpStatus;

/** JwtAuthenticationException. */
public class JwtAuthenticationException extends AbstractAnteikuException {

  public JwtAuthenticationException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
