package com.my.blog.myblogbe.service.impl.impl;

import com.my.blog.myblogbe.database.entity.posts.CommentaryEntity;
import com.my.blog.myblogbe.database.entity.posts.PostEntity;
import com.my.blog.myblogbe.database.repository.CommentaryRepository;
import com.my.blog.myblogbe.database.repository.PostRepository;
import com.my.blog.myblogbe.service.impl.api.PostService;
import com.my.blog.myblogbe.service.impl.api.UserService;
import com.my.blog.myblogbe.service.impl.mappers.ServiceLayerMapper;
import com.my.blog.myblogbe.service.impl.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.impl.model.post.PostModel;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;
  private CommentaryRepository commentaryRepository;
  private UserService userService;

  @Override
  public PostModel getPostById(String postId) {
    return ServiceLayerMapper.I.postEntityToModel(
        postRepository.findById(postId).orElse(new PostEntity()));
  }

  @Override
  public List<PostModel> getAllPosts() {
    return ServiceLayerMapper.I.postEntitiesToModels(postRepository.findAll());
  }

  @Override
  public PostModel createPost(PostModel postModel) {
    PostEntity postEntity =
        PostEntity.builder()
            .creatorId(userService.getAuthenticatedUser().getId())
            .theme(postModel.getTheme())
            .content(postModel.getContent())
            .createdOn(LocalDateTime.now())
            .build();
    return ServiceLayerMapper.I.postEntityToModel(postRepository.save(postEntity));
  }

  @Override
  public CommentaryModel createPostCommentary(CommentaryModel commentaryModel) {
    CommentaryEntity commentaryEntity =
        ServiceLayerMapper.I.commentaryModelToEntity(commentaryModel);
    commentaryEntity.setCreatorId(userService.getAuthenticatedUser().getId());
    commentaryEntity.setCreatedOn(LocalDateTime.now());
    return ServiceLayerMapper.I.commentaryEntityToModel(
        commentaryRepository.save(commentaryEntity));
  }
}
