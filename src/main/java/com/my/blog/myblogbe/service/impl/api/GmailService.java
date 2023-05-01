package com.my.blog.myblogbe.service.impl.api;


import com.my.blog.myblogbe.service.impl.model.UserModel;

public interface GmailService {
    void sendAccountActivationLink(UserModel userModel);
    void sendAccountForgotPasswordLink(String email);
}
