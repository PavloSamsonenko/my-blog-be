package com.my.blog.myblogbe.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** AuthorizationTokenResponseDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationTokenResponseDto {
  private String authorizationToken;
}
