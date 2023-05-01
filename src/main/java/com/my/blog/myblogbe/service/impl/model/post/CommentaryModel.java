package com.my.blog.myblogbe.service.impl.model.post;

import com.my.blog.myblogbe.service.impl.model.UserModel;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentaryModel {
  private String id;
  private String commentary;
  private UserModel creator;
  private String postId;
  private String creatorId;
  private LocalDateTime createdOn;
}
