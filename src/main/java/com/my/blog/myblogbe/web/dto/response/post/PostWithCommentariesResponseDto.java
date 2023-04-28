package com.my.blog.myblogbe.web.dto.response.post;

import com.my.blog.myblogbe.web.dto.response.UserResponseDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostWithCommentariesResponseDto {
  private String id;
  private UserResponseDto creator;
  private String theme;
  private String content;
  private LocalDateTime createdOn;
}
