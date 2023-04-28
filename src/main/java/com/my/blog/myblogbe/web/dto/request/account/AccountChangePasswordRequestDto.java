package com.my.blog.myblogbe.web.dto.request.account;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountChangePasswordRequestDto {
  @NotEmpty(message = "Password cannot be empty")
  private String newPassword;
}
