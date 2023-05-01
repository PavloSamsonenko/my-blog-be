package com.my.blog.myblogbe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.my.blog.myblogbe.database.entity.posts.CommentaryEntity;
import com.my.blog.myblogbe.database.entity.posts.PostEntity;
import com.my.blog.myblogbe.database.repository.CommentaryRepository;
import com.my.blog.myblogbe.database.repository.PostRepository;
import com.my.blog.myblogbe.service.api.PostService;
import com.my.blog.myblogbe.service.api.UserService;
import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.service.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.model.post.PostModel;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {
  private PostService postService;
  @Mock private PostRepository postRepository;
  @Mock private CommentaryRepository commentaryRepository;
  @Mock private UserService userService;

  @BeforeEach
  public void init() {
    this.postService = new PostServiceImpl(postRepository, commentaryRepository, userService);
  }

  @Test
  void testGetPostByIdReturnEmpty() {
    String mockPostId = "test";
    when(postRepository.findById(mockPostId)).thenReturn(Optional.empty());
    PostModel excpectedResult = new PostModel();
    PostModel actualResult = postService.getPostById(mockPostId);
    assertEquals(actualResult, excpectedResult);
  }

  @Test
  void testGetPostByIdReturnNonEmpty() {
    String mockContent = "MockContent";
    String mockPostId = "test";
    PostEntity mockPostEntity = PostEntity.builder().content(mockContent).id(mockPostId).build();
    when(postRepository.findById(mockPostId)).thenReturn(Optional.of(mockPostEntity));
    PostModel excpectedResult = PostModel.builder().id(mockPostId).content(mockContent).build();
    PostModel actualResult = postService.getPostById(mockPostId);
    assertEquals(actualResult, excpectedResult);
  }

  @Test
  void testGetAllPosts() {
    when(postRepository.findAll()).thenReturn(List.of());
    List<PostModel> actualResult = postService.getAllPosts();
    assertEquals(actualResult, List.of());
  }

  @Test
  void testCreatePost() {
    String mockContent = "MockContent";
    PostEntity mockEntity = PostEntity.builder().content(mockContent).build();
    String mockUserId = "mockUserId";
    UserModel mockUserModel = UserModel.builder().id(mockUserId).build();
    when(postRepository.save(any())).thenReturn(mockEntity);
    when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
    PostModel mockPost = PostModel.builder().content(mockContent).build();
    PostModel expectedResult = PostModel.builder().content(mockContent).build();
    PostModel actualResult = postService.createPost(mockPost);
    assertEquals(actualResult, expectedResult);
  }

  @Test
  void testCreatePostCommentary() {
    String mockCommentary = "mockCommentary";
    String mockUserId = "mockUserId";
    CommentaryEntity mockCommentaryEntity =
        CommentaryEntity.builder().commentary(mockCommentary).creatorId(mockUserId).build();
    CommentaryModel mockCommentaryModel =
        CommentaryModel.builder().commentary(mockCommentary).creatorId(mockUserId).build();
    UserModel mockUserModel = UserModel.builder().id(mockUserId).build();
    when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
    when(commentaryRepository.save(any())).thenReturn(mockCommentaryEntity);
    CommentaryModel expectedResult =
        CommentaryModel.builder().commentary(mockCommentary).creatorId(mockUserId).build();
    CommentaryModel actualResult = postService.createPostCommentary(mockCommentaryModel);
    assertEquals(actualResult, expectedResult);
  }
}
