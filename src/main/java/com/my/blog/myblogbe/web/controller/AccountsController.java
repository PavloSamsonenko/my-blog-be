package com.my.blog.myblogbe.web.controller;

import com.my.blog.myblogbe.service.api.GmailService;
import com.my.blog.myblogbe.service.api.JwtService;
import com.my.blog.myblogbe.service.api.UserService;
import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.service.model.enums.JwtTypeEnum;
import com.my.blog.myblogbe.web.dto.request.AccountCredentialsRequestDto;
import com.my.blog.myblogbe.web.dto.request.AccountEmailRequestDto;
import com.my.blog.myblogbe.web.dto.response.AccountRegistrationResponseDto;
import com.my.blog.myblogbe.web.mappers.WebLayerMapper;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Account controller. */
@AllArgsConstructor
@RestController
@RequestMapping("/a/rest/accounts")
public class AccountsController {
  private final UserService userService;
  private final JwtService jwtService;
  private final GmailService gmailService;

  /**
   * Account registration endpoint.
   *
   * @param requestDto AccountCredentialsRequestDto.
   * @return AccountRegistrationResponseDto.
   */
  @PostMapping("/register")
  public ResponseEntity<AccountRegistrationResponseDto> registerAccount(
      @RequestBody @Valid AccountCredentialsRequestDto requestDto) {
    UserModel userModel =
        userService.registerUser(WebLayerMapper.I.accountCredentialsRequestDtoToUser(requestDto));
    gmailService.sendAccountActivationLink(userModel);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            AccountRegistrationResponseDto.builder()
                .user(WebLayerMapper.I.userModelToResponseDto(userModel))
                .jwt(jwtService.createJwt(userModel.getEmail(), JwtTypeEnum.AUTHORIZATION))
                .build());
  }

  /**
   * Account login endpoint.
   *
   * @param requestDto AccountCredentialsRequestDto
   * @return Authorization jwt token.
   */
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> loginInAccount(
      @RequestBody @Valid AccountCredentialsRequestDto requestDto) {
    userService.isCorrectPassword(WebLayerMapper.I.accountCredentialsRequestDtoToUser(requestDto));

    return ResponseEntity.ok(
        Map.of(
            "authorization token",
            jwtService.createJwt(
                WebLayerMapper.I.accountCredentialsRequestDtoToUser(requestDto).getEmail(),
                JwtTypeEnum.AUTHORIZATION)));
  }

  /**
   * Account email activation endpoint.
   *
   * @param activationToken email activation token.
   * @return ok if done.
   */
  @PostMapping("/email/activation/{activationToken}")
  public ResponseEntity<HttpStatus> activateAccount(
      @PathVariable(value = "activationToken") String activationToken) {
    if (jwtService.isEmailActivationToken(activationToken)) {
      userService.activateUserByEmail(jwtService.getEmailFromJwt(activationToken));
    }
    return ResponseEntity.ok().build();
  }

  /**
   * Send email for forgotten password endpoint.
   *
   * @param requestDto request dto.
   * @return ok if done.
   */
  @PostMapping("/password/forgot")
  public ResponseEntity<HttpStatus> getEmailForForgottenPassword(
      @RequestBody @Valid AccountEmailRequestDto requestDto) {
    if (userService.userExistByEmail(requestDto.getEmail())) {
      gmailService.sendAccountForgotPasswordLink(requestDto.getEmail());
    }
    return ResponseEntity.ok().build();
  }

  /**
   * Account forgotten password change endpoint.
   *
   * @param passwordResetToken password reset token.
   * @param newPassword New password.
   * @return ok if done.
   */
  @PostMapping("/password/forgot/change/{passwordResetToken}")
  public ResponseEntity<HttpStatus> changeForgottenAccountPassword(
      @PathVariable(value = "passwordResetToken") String passwordResetToken, String newPassword) {
    if (jwtService.isPasswordForgottenToken(passwordResetToken)) {
      userService.changeUserPasswordByEmail(
          jwtService.getEmailFromJwt(passwordResetToken), newPassword);
    }
    return ResponseEntity.ok().build();
  }
}
