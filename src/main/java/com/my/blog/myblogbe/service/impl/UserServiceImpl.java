package com.my.blog.myblogbe.service.impl;

import com.my.blog.myblogbe.database.entity.UserEntity;
import com.my.blog.myblogbe.database.repository.UserRepository;
import com.my.blog.myblogbe.service.api.UserService;
import com.my.blog.myblogbe.service.mappers.ServiceLayerMapper;
import com.my.blog.myblogbe.service.model.SecurityUserModel;
import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.web.exceptions.model.IncorrectCredentialsException;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // Add preload users for testing
  @PostConstruct
  public void init() {
    if (userRepository.getUserEntityByEmail("test@test").isEmpty()) {
      UserEntity testUser =
          ServiceLayerMapper.I.userModelToEntity(
              UserModel.builder()
                  .email("test@test")
                  .password(passwordEncoder.encode("test"))
                  .username("test")
                  .createdOn(LocalDateTime.now())
                  .enabled(true)
                  .role(SecurityUserModel.Roles.ROLE_USER)
                  .build());
      userRepository.save(testUser);
    }
  }

  @Override
  public SecurityUserModel getUserByEmail(String email) {
    return ServiceLayerMapper.I.userEntityToSecurityUserModel(
        userRepository
            .getUserEntityByEmail(email)
            .orElseThrow(
                () ->
                    new IncorrectCredentialsException(
                        Map.of("email", String.format("User, with email '%s', not found", email)),
                        "Incorrect email")));
  }

  @Override
  public UserModel registerUser(UserModel userModel) {
    userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    userModel.setCreatedOn(LocalDateTime.now());
    userModel.setEnabled(false);
    userModel.setRole(SecurityUserModel.Roles.ROLE_USER);

    return ServiceLayerMapper.I.userEntityToModel(
        Optional.of(userRepository.save(ServiceLayerMapper.I.userModelToEntity(userModel)))
            .orElseThrow(() -> new RuntimeException("Error while trying to save the user")));
  }

  @Override
  public UserModel updateUser(UserModel userModel) {
    return ServiceLayerMapper.I.userEntityToModel(
        userRepository.save(
            ServiceLayerMapper.I.updateUserEntityFromModel(
                userModel, userRepository.findById(getAuthenticatedUser().getId()).orElseThrow())));
  }

  public boolean isCorrectPassword(UserModel userModel) {
    if (!passwordEncoder.matches(
        userModel.getPassword(), getUserByEmail(userModel.getEmail()).getPassword())) {
      throw new IncorrectCredentialsException(
          Map.of("password", "Please enter correct password"), "Incorrect password");
    }
    return true;
  }

  @Override
  public void activateUserByEmail(String email) {
    userRepository.findByEmailAndEnable(email);
  }

  @Override
  public void changeUserPasswordByEmail(String email, String newPassword) {
    userRepository.findByEmailAndChangePassword(email, passwordEncoder.encode(newPassword));
  }

  @Override
  public boolean userExistByEmail(String email) {
    return userRepository.getUserEntityByEmail(email).isPresent();
  }

  @Override
  public UserModel getAuthenticatedUser() {
    return ServiceLayerMapper.I.securityUserModelToModel(
        (SecurityUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }
}
