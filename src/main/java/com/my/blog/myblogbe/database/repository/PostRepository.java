package com.my.blog.myblogbe.database.repository;

import com.my.blog.myblogbe.database.entity.posts.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
  List<PostEntity> findAllByCreatorId(String creatorId);
}
