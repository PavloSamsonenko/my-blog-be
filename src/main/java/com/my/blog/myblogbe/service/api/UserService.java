package com.my.blog.myblogbe.service.api;

import com.my.blog.myblogbe.database.entity.UserEntity;
import com.my.blog.myblogbe.service.model.SecurityUserModel;
import com.my.blog.myblogbe.service.model.UserModel;

/** Interface for user operations. */
public interface UserService {
  /**
   * Get SecurityUserModel by email.
   *
   * @param email email.
   * @return SecurityUser.
   */
  SecurityUserModel getUserByEmail(String email);

  /**
   * Register user.
   *
   * @param userModel UserModel with credentials.
   * @return UserModel of created user.
   */
  UserModel registerUser(UserModel userModel);

  /**
   * Test method
   *
   * @return User entity of created test user.
   */
  UserEntity createTestUser();

  /**
   * Check if password is correct.
   *
   * @param userModel userModel with credentials.
   * @return true if correct.
   */
  boolean isCorrectPassword(UserModel userModel);

  /**
   * Activate user by email.
   *
   * @param email user email.
   */
  void activateUserByEmail(String email);

  /**
   * Change user password by email.
   *
   * @param email user email.
   * @param newPassword user new password.
   */
  void changeUserPasswordByEmail(String email, String newPassword);

  /**
   * Check if user exist by email.
   *
   * @param email user email.
   * @return true or false.
   */
  boolean userExistByEmail(String email);
}
