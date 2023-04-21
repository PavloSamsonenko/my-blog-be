package com.my.blog.myblogbe.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class AccountRegistrationResponseDto {
  String jwt;
  UserModelResponseDto user;
}
