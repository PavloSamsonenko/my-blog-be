package com.my.blog.myblogbe.web.controller;

import com.my.blog.myblogbe.service.api.JwtService;
import com.my.blog.myblogbe.service.api.UserService;
import com.my.blog.myblogbe.service.model.enums.JwtTypeEnum;
import com.my.blog.myblogbe.web.dto.request.account.AccountChangePasswordRequestDto;
import com.my.blog.myblogbe.web.dto.request.user.UserDataRequestDto;
import com.my.blog.myblogbe.web.dto.response.AuthorizationTokenResponseDto;
import com.my.blog.myblogbe.web.dto.response.UserResponseDto;
import com.my.blog.myblogbe.web.mappers.WebLayerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/a/rest/user")
public class UserController {
  private UserService userService;
  private JwtService jwtService;

  @GetMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<UserResponseDto> getUser() {
    return ResponseEntity.ok(
        WebLayerMapper.I.userModelToResponseDto(userService.getAuthenticatedUser()));
  }

  @PatchMapping("/data")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<AuthorizationTokenResponseDto> changeUserData(
      @Valid @RequestBody UserDataRequestDto requestDto) {
    userService.updateUser(WebLayerMapper.I.userDataRequestDtoToUserModel(requestDto));
    return ResponseEntity.ok(
        AuthorizationTokenResponseDto.builder()
            .authorizationToken(
                jwtService.createJwt(
                    (requestDto.getEmail() == null)
                        ? userService.getAuthenticatedUser().getEmail()
                        : requestDto.getEmail(),
                    JwtTypeEnum.AUTHORIZATION))
            .build());
  }

  @PatchMapping("/password")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<HttpStatus> changeUserPassword(
      @Valid @RequestBody AccountChangePasswordRequestDto requestDto) {
    userService.changeUserPasswordByEmail(
        userService.getAuthenticatedUser().getEmail(), requestDto.getNewPassword());
    return ResponseEntity.ok().build();
  }
}
