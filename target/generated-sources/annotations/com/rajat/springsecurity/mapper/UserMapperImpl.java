package com.rajat.springsecurity.mapper;

import com.rajat.springsecurity.entity.UserEntity;
import com.rajat.springsecurity.model.UserRegistrationDTO;
import com.rajat.springsecurity.model.UserResponseDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-18T16:31:50+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity registerDTOToEntity(UserRegistrationDTO userRegistrationDTO) {
        if ( userRegistrationDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( userRegistrationDTO.getUsername() );
        userEntity.setEmail( userRegistrationDTO.getEmail() );
        userEntity.setPassword( userRegistrationDTO.getPassword() );

        return userEntity;
    }

    @Override
    public UserResponseDTO entityToResponseDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUsername( userEntity.getUsername() );

        return userResponseDTO;
    }
}
