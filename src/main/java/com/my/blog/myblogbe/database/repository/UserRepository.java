package com.my.blog.myblogbe.database.repository;

import com.my.blog.myblogbe.database.entity.UserEntity;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> getUserEntityByEmail(String email);

  @Transactional
  @Modifying
  @Query("update UserEntity u set u.enabled = true where u.email = :email")
  void findByEmailAndEnable(@Param(value = "email") String email);

  @Transactional
  @Modifying
  @Query("update UserEntity u set u.password = :newPassword where u.email = :email")
  void findByEmailAndChangePassword(
      @Param(value = "email") String email, @Param(value = "newPassword") String newPassword);
}
