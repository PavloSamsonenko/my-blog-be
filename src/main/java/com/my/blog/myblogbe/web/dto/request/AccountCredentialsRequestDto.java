package com.my.blog.myblogbe.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentialsRequestDto {
  @Email(
      message = "Email is not valid",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotEmpty(message = "Email cannot be empty")
  private String email;

  @NotEmpty(message = "Password cannot be empty")
  private String password;
}
