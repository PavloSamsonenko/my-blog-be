package com.my.blog.myblogbe.service.impl.model;

import com.my.blog.myblogbe.service.impl.model.SecurityUserModel.Roles;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserModel {
  private String id;
  private String email;
  private String username;
  private String password;
  private LocalDateTime createdOn;
  private Boolean enabled;
  private Roles role;
}
