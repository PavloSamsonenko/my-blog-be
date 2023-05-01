package com.my.blog.myblogbe.service.api;

import com.my.blog.myblogbe.service.model.UserModel;

/**
 * GmailService.
 */
public interface GmailService {
  void sendAccountActivationLink(UserModel userModel);

  void sendAccountForgotPasswordLink(String email);
}
