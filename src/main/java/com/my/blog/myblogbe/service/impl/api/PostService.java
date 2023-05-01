package com.my.blog.myblogbe.service.impl.api;

import com.my.blog.myblogbe.service.impl.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.impl.model.post.PostModel;
import java.util.List;

public interface PostService {
  PostModel getPostById(String postId);

  List<PostModel> getAllPosts();

  PostModel createPost(PostModel postModel);

  CommentaryModel createPostCommentary(CommentaryModel commentaryModel);
}
