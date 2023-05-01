package com.my.blog.myblogbe.database.repository;

import com.my.blog.myblogbe.database.entity.posts.CommentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** CommentaryRepository. */
@Repository
public interface CommentaryRepository extends JpaRepository<CommentaryEntity, String> {}
