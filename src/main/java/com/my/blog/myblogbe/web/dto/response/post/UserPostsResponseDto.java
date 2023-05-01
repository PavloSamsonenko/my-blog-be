package com.my.blog.myblogbe.web.dto.response.post;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserPostsResponseDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPostsResponseDto {
  List<PostResponseDto> userPosts;
}
