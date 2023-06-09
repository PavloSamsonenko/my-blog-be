package com.my.blog.myblogbe.service.impl;

import com.my.blog.myblogbe.service.api.GmailService;
import com.my.blog.myblogbe.service.api.JwtService;
import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.service.model.enums.JwtTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** GmailServiceImpl. */
@Service
@RequiredArgsConstructor
public class GmailServiceImpl implements GmailService {
  private final JavaMailSender emailSender;
  private final JwtService jwtService;

  private void sendMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }

  @Override
  public void sendAccountActivationLink(UserModel userModel) {
    sendMessage(
        userModel.getEmail(),
        "Activation link",
        "Please click here to activate your account:"
            + " http://localhost:3000/account/email/activation/"
            + jwtService.createJwt(userModel.getEmail(), JwtTypeEnum.EMAIL_ACTIVATION)
            + ".\n Link will expire in 10 minutes.");
  }

  @Override
  public void sendAccountForgotPasswordLink(String email) {
    sendMessage(
        email,
        "Forgot your password?",
        "Please click here to change your password:"
            + "http://localhost:3000/account/password/forget/"
            + jwtService.createJwt(email, JwtTypeEnum.PASSWORD_FORGOT)
            + ".\n Link will expire in 10 minutes.");
  }
}
