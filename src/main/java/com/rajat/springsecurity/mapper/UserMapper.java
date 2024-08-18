package com.rajat.springsecurity.mapper;


import com.rajat.springsecurity.entity.UserEntity;
import com.rajat.springsecurity.model.UserRegistrationDTO;
import com.rajat.springsecurity.model.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapping RegisterDTO to EntityDTO
    UserEntity registerDTOToEntity(UserRegistrationDTO userRegistrationDTO);

    // Mapping EntityDTO to ResponseDTO
    @Mapping(source = "username", target = "username")
    UserResponseDTO entityToResponseDTO(UserEntity userEntity);
}
