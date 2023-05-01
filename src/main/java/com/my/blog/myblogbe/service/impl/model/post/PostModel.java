package com.my.blog.myblogbe.service.impl.model.post;

import com.my.blog.myblogbe.service.impl.model.UserModel;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostModel {
  private String id;
  private UserModel creator;
  private String theme;
  private String content;
  private List<CommentaryModel> commentaries;
  private LocalDateTime createdOn;
}
