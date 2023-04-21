package com.my.blog.myblogbe.service.model;


import com.my.blog.myblogbe.service.model.SecurityUserModel.Roles;
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
  private String password;
  private LocalDateTime createdOn;
  private Boolean enabled;
  private Roles role;
}
