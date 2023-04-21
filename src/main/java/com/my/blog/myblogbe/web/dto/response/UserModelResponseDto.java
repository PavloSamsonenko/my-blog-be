package com.my.blog.myblogbe.web.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModelResponseDto {
  private String email;
  private Boolean enabled;
  private LocalDateTime createdOn;
}
