package com.my.blog.myblogbe.web.controller;

import com.my.blog.myblogbe.service.api.PostService;
import com.my.blog.myblogbe.web.dto.request.post.CommentaryRequestDto;
import com.my.blog.myblogbe.web.dto.request.post.PostRequestDto;
import com.my.blog.myblogbe.web.dto.response.post.CommentaryResponseDto;
import com.my.blog.myblogbe.web.dto.response.post.PostResponseDto;
import com.my.blog.myblogbe.web.dto.response.post.UserPostsResponseDto;
import com.my.blog.myblogbe.web.mappers.WebLayerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** PostsController. */
@AllArgsConstructor
@RestController
@RequestMapping("/a/rest/posts")
public class PostsController {
  private PostService postService;

  /**
   * Get all posts.
   *
   * @return AccountRegistrationResponseDto.
   */
  @GetMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<UserPostsResponseDto> getAllPosts() {
    return ResponseEntity.ok(
        UserPostsResponseDto.builder()
            .userPosts(WebLayerMapper.I.postModelsToResponseDto(postService.getAllPosts()))
            .build());
  }

  /**
   * Get post by id.
   *
   * @return AccountRegistrationResponseDto.
   */
  @GetMapping("/{postId}")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<PostResponseDto> getPostById(
      @PathVariable(value = "postId") String postId) {
    return ResponseEntity.ok(
        WebLayerMapper.I.postModelToResponseDto(postService.getPostById(postId)));
  }

  /**
   * Create new post.
   *
   * @return AccountRegistrationResponseDto.
   */
  @PostMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<PostResponseDto> createNewPost(
      @Valid @RequestBody PostRequestDto requestDto) {
    return ResponseEntity.ok(
        WebLayerMapper.I.postModelToResponseDto(
            postService.createPost(WebLayerMapper.I.postRequestDtoToModel(requestDto))));
  }

  /**
   * Create new commentary.
   *
   * @return CommentaryResponseDto.
   */
  @PostMapping("/commentary")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<CommentaryResponseDto> createNewPostCommentary(
      @Valid @RequestBody CommentaryRequestDto requestDto) {
    return ResponseEntity.ok(
        WebLayerMapper.I.commentaryModelToResponseDto(
            postService.createPostCommentary(
                WebLayerMapper.I.commentaryRequestDtoToModel(requestDto))));
  }
}
