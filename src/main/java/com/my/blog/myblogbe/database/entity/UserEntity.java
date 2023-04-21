package com.my.blog.myblogbe.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "email", length = 128, unique = true)
  private String email;

  @Column(name = "roles")
  @Enumerated(EnumType.ORDINAL)
  private Roles role;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled", columnDefinition = "boolean default false")
  private Boolean enabled;

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  public static enum Roles {
    ROLE_SUPERADMIN,
    ROLE_ADMIN,
    ROLE_USER
  }
}
