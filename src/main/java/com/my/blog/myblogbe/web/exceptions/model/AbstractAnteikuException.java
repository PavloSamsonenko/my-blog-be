package com.my.blog.myblogbe.web.exceptions.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/** AbstractAnteikuException. */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class AbstractAnteikuException extends RuntimeException {
  private HttpStatus httpStatus;
  private Map<String, String> errors;
  private String message;
}
