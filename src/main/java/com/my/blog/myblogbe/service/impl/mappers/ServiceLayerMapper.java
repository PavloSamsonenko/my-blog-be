package com.my.blog.myblogbe.service.impl.mappers;

import com.my.blog.myblogbe.database.entity.UserEntity;
import com.my.blog.myblogbe.database.entity.posts.CommentaryEntity;
import com.my.blog.myblogbe.database.entity.posts.PostEntity;
import com.my.blog.myblogbe.service.impl.model.SecurityUserModel;
import com.my.blog.myblogbe.service.impl.model.UserModel;
import com.my.blog.myblogbe.service.impl.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.impl.model.post.PostModel;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
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
   * SecurityUserModel to UserModel mapper.
   *
   * @param securityUserModel SecurityUserModel.
   * @return SecurityUserModel.
   */
  UserModel securityUserModelToModel(SecurityUserModel securityUserModel);

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
   * Update UserEntity From UserModel.
   *
   * @param userModel UserModel.
   * @return UserEntity.
   */
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  UserEntity updateUserEntityFromModel(UserModel userModel, @MappingTarget UserEntity userEntity);

  /**
   * PostEntity to PostModel.
   *
   * @param postEntity PostEntity.
   * @return PostModel.
   */
  PostModel postEntityToModel(PostEntity postEntity);

  /**
   * List of PostEntity to List of PostModel.
   *
   * @param postEntity PostEntity.
   * @return List of PostModel.
   */
  List<PostModel> postEntitiesToModels(List<PostEntity> postEntity);

  /**
   * CommentaryModel to CommentaryEntity.
   *
   * @param commentaryModel CommentaryModel.
   * @return CommentaryEntity.
   */
  CommentaryEntity commentaryModelToEntity(CommentaryModel commentaryModel);

  /**
   * CommentaryEntity to Commentary.
   *
   * @param commentaryEntity CommentaryEntity.
   * @return Commentary.
   */
  CommentaryModel commentaryEntityToModel(CommentaryEntity commentaryEntity);

  /**
   * List of CommentaryEntity to List of Commentary.
   *
   * @param commentaryEntities List of CommentaryEntity.
   * @return List of Commentary.
   */
  List<CommentaryModel> commentaryEntitiesToModels(List<CommentaryEntity> commentaryEntities);
}
