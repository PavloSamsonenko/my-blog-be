package com.my.blog.myblogbe.web.mappers;

import com.my.blog.myblogbe.service.model.UserModel;
import com.my.blog.myblogbe.service.model.post.CommentaryModel;
import com.my.blog.myblogbe.service.model.post.PostModel;
import com.my.blog.myblogbe.web.dto.request.account.AccountCredentialsRequestDto;
import com.my.blog.myblogbe.web.dto.request.account.AccountLoginRequestDto;
import com.my.blog.myblogbe.web.dto.request.post.CommentaryRequestDto;
import com.my.blog.myblogbe.web.dto.request.post.PostRequestDto;
import com.my.blog.myblogbe.web.dto.request.user.UserDataRequestDto;
import com.my.blog.myblogbe.web.dto.response.UserResponseDto;
import com.my.blog.myblogbe.web.dto.response.post.CommentaryResponseDto;
import com.my.blog.myblogbe.web.dto.response.post.PostResponseDto;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for web layer.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, builder = @Builder(disableBuilder = true))
public interface WebLayerMapper {

  /**
   * Instance.
   */
  WebLayerMapper I = Mappers.getMapper(WebLayerMapper.class);

  /**
   * UserDataRequestDto to UserModel mapper.
   *
   * @param requestDto UserDataRequestDto.
   * @return UserModel.
   */
  UserModel userDataRequestDtoToUserModel(UserDataRequestDto requestDto);

  /**
   * AccountCredentialsRequestDto to UserModel mapper.
   *
   * @param requestDto AccountCredentialsRequestDto.
   * @return UserModel.
   */
  UserModel accountCredentialsRequestDtoToUser(AccountCredentialsRequestDto requestDto);

  /**
   * AccountLoginRequestDto to UserModel mapper.
   *
   * @param requestDto AccountLoginRequestDto.
   * @return UserModel.
   */
  UserModel accountLoginRequestDtoToUser(AccountLoginRequestDto requestDto);

  /**
   * PostRequestDto to PostModel mapper.
   *
   * @param requestDto PostRequestDto.
   * @return PostModel.
   */
  PostModel postRequestDtoToModel(PostRequestDto requestDto);

  /**
   * PostModel to PostResponseDto mapper.
   *
   * @param postModel PostModel.
   * @return PostResponseDto.
   */
  PostResponseDto postModelToResponseDto(PostModel postModel);

  /**
   * UserModel to UserModelResponseDto mapper.
   *
   * @param userModel UserModel.
   * @return UserModelResponseDto.
   */
  UserResponseDto userModelToResponseDto(UserModel userModel);

  /**
   * List of PostModel to List of PostResponseDto mapper.
   *
   * @param postModel PostModel
   * @return PostResponseDto
   */
  List<PostResponseDto> postModelsToResponseDto(List<PostModel> postModel);

  /**
   * CommentaryModel to CommentaryResponseDto.
   *
   * @param commentaryModel CommentaryModel.
   * @return CommentaryResponseDto.
   */
  CommentaryResponseDto commentaryModelToResponseDto(CommentaryModel commentaryModel);

  /**
   * CommentaryRequestDto to CommentaryModel mapper.
   *
   * @param commentaryRequestDto CommentaryRequestDto.
   * @return CommentaryModel.
   */
  CommentaryModel commentaryRequestDtoToModel(CommentaryRequestDto commentaryRequestDto);

  /**
   * List of CommentaryModel to List of CommentaryResponseDto.
   *
   * @param commentaryModels List of CommentaryModel.
   * @return List of CommentaryResponseDto.
   */
  List<CommentaryResponseDto> commentaryModelsToResponseDtoList(
      List<CommentaryModel> commentaryModels);
}
