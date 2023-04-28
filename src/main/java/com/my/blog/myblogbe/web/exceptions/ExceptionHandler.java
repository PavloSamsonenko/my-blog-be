package com.my.blog.myblogbe.web.exceptions;

import com.my.blog.myblogbe.web.dto.response.ApplicationExceptionResponseDto;
import com.my.blog.myblogbe.web.exceptions.model.AbstractAnteikuException;
import com.my.blog.myblogbe.web.exceptions.model.DataNotFoundException;
import com.my.blog.myblogbe.web.exceptions.model.IncorrectCredentialsException;
import com.my.blog.myblogbe.web.exceptions.model.JwtAuthenticationException;
import com.my.blog.myblogbe.web.exceptions.model.UserNotActivatedException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
  private static final Map<Map<String, String>, String> CONSTRAINS_MAP =
      Map.of(
          Map.of("email", "Email already exist"),
          "Ключ \"(email)=",
          Map.of("postId", "Post not found"),
          "Ключ (post_id)");

  /**
   * @param exception - MethodArgumentNotValidException
   * @return ResponseEntity<ApplicationExceptionResponseDto>
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleException(
      MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : exception.getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    for (ObjectError objectError : exception.getGlobalErrors()) {
      errors.put(objectError.getObjectName(), objectError.getDefaultMessage());
    }
    return buildResponseDtoAndGetResponseEntity(
        "Input validation error", HttpStatus.UNPROCESSABLE_ENTITY, errors);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleException(
      DataIntegrityViolationException exception) {
    String rootMsg = exception.getRootCause().getMessage();
    Map<String, String> errors = new HashMap<>();
    if (rootMsg != null) {
      for (Map.Entry<Map<String, String>, String> entry : CONSTRAINS_MAP.entrySet()) {
        if (rootMsg.contains(entry.getValue())) {
          Map.Entry<String, String> errorsMap = entry.getKey().entrySet().iterator().next();
          errors.put(errorsMap.getKey(), errorsMap.getValue());
        }
      }
    }
    if (errors.isEmpty())
      errors.put("unknownError", "Unknown data operation error, please contact support");
    return buildResponseDtoAndGetResponseEntity(
        "Data operation error", HttpStatus.CONFLICT, errors);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler({
    IncorrectCredentialsException.class,
    JwtAuthenticationException.class,
    UserNotActivatedException.class,
    DataNotFoundException.class
  })
  public <T extends AbstractAnteikuException>
      ResponseEntity<ApplicationExceptionResponseDto> handleException(T exception) {
    return buildResponseDtoAndGetResponseEntity(
        exception.getMessage(), HttpStatus.UNAUTHORIZED, exception.getErrors());
  }

  private ResponseEntity<ApplicationExceptionResponseDto> buildResponseDtoAndGetResponseEntity(
      String message, HttpStatus status, Map<String, String> errors) {
    return ResponseEntity.status(status)
        .body(
            ApplicationExceptionResponseDto.builder()
                .message(message)
                .status(status.value())
                .errors(errors)
                .build());
  }
}
