package com.my.blog.myblogbe.service.api;

import com.my.blog.myblogbe.service.model.enums.JwtTypeEnum;

/** Interface for Jwt operations. */
public interface JwtService {

  /**
   * Create Jwt.
   *
   * @param email User email.
   * @return Jwt token.
   */
  String createJwt(String email, JwtTypeEnum type);

  /**
   * Get email from Jwt.
   *
   * @param jwt Jwt token.
   * @return User email extracted from jwt.
   */
  String getEmailFromJwt(String jwt);

  /**
   * Check if token is authorization token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isAuthorizationToken(String jwt);

  /**
   * Check if token is email activation token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isEmailActivationToken(String jwt);

  /**
   * Check if token is password forgotten token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isPasswordForgottenToken(String jwt);
}
