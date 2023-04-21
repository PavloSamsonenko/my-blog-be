package com.my.blog.myblogbe.service.mappers;


import com.my.blog.myblogbe.database.entity.UserEntity;
import com.my.blog.myblogbe.service.model.SecurityUserModel;
import com.my.blog.myblogbe.service.model.UserModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/** Mapper for service layer */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, builder = @Builder(disableBuilder = true))
public interface ServiceLayerMapper {
  /** Instance. */
  ServiceLayerMapper I = Mappers.getMapper(ServiceLayerMapper.class);

  /**
   * UserEntity to SecurityUserModel mapper.
   *
   * @param userEntity userEntity.
   * @return SecurityUserModel.
   */
  SecurityUserModel userEntityToSecurityUserModel(UserEntity userEntity);

  /**
   * UserEntity to UserModel mapper.
   *
   * @param userEntity UserEntity.
   * @return UserModel.
   */
  UserModel userEntityToModel(UserEntity userEntity);

  /**
   * UserModel to UserEntity mapper.
   *
   * @param userModel UserModel.
   * @return UserEntity.
   */
  UserEntity userModelToEntity(UserModel userModel);

  /**
   * SecurityUserModel to UserModel mapper.
   *
   * @param securityUserModel SecurityUserModel.
   * @return UserModel.
   */
  UserModel securityUserModelToUserModel(SecurityUserModel securityUserModel);
}
