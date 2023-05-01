package com.my.blog.myblogbe.service.api;

import com.my.blog.myblogbe.service.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.model.post.PostModel;
import java.util.List;

/** PostService. */
public interface PostService {
  PostModel getPostById(String postId);

  List<PostModel> getAllPosts();

  PostModel createPost(PostModel postModel);

  CommentaryModel createPostCommentary(CommentaryModel commentaryModel);
}
