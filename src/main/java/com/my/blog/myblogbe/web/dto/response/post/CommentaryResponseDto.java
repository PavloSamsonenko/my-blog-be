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
public class CommentaryResponseDto {
  private String id;
  private String commentary;
  private UserResponseDto creator;
  private String creatorId;
  private LocalDateTime createdOn;
}
