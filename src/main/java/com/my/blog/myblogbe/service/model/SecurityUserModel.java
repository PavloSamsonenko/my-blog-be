package com.my.blog.myblogbe.service.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SecurityUserModel implements UserDetails {
  private String id;
  private String email;
  private String username;
  private String password;
  private Boolean enabled;
  private LocalDateTime createdOn;
  private Roles role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(role);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public static enum Roles implements GrantedAuthority {
    ROLE_SUPERADMIN,
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
      return name();
    }
  }
}
