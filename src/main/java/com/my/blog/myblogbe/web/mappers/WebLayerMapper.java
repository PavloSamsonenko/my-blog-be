package com.my.blog.myblogbe.web.mappers;


import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.web.dto.request.AccountCredentialsRequestDto;
import com.my.blog.myblogbe.web.dto.response.UserModelResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/** Mapper for web layer */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, builder = @Builder(disableBuilder = true))
public interface WebLayerMapper {
  /** Instance. */
  WebLayerMapper I = Mappers.getMapper(WebLayerMapper.class);

  /**
   * AccountCredentialsRequestDto to UserModel mapper.
   *
   * @param requestDto AccountCredentialsRequestDto.
   * @return UserModel.
   */
  UserModel accountCredentialsRequestDtoToUser(AccountCredentialsRequestDto requestDto);

  /**
   * UserModel to UserModelResponseDto mapper.
   *
   * @param userModel UserModel.
   * @return UserModelResponseDto.
   */
  UserModelResponseDto userModelToResponseDto(UserModel userModel);
}
