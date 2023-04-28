package com.my.blog.myblogbe.web.dto.response.post;

import com.my.blog.myblogbe.web.dto.response.post.PostResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPostsResponseDto {
  List<PostResponseDto> userPosts;
}
