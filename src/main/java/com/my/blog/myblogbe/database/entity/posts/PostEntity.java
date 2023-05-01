package com.my.blog.myblogbe.database.entity.posts;

import com.my.blog.myblogbe.database.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * PostEntity.
 */
@Data
@Table(name = "posts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "post_id")
  private String id;

  @Column(name = "theme")
  private String theme;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, insertable = false, updatable = false)
  private UserEntity creator;

  @Column(name = "creator_id")
  private String creatorId;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<CommentaryEntity> commentaries;

  @Column(name = "created_on")
  private LocalDateTime createdOn;
}
