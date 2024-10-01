package org.boubyan.studentms.mapper;

import org.boubyan.studentms.model.dtos.response.RegisterUserResponseDto;
import org.boubyan.studentms.model.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	RegisterUserResponseDto toRegisterUserResponseDto(UserEntity userEntity);

}
