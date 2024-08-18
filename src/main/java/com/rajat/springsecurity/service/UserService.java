package com.rajat.springsecurity.service;

import com.rajat.springsecurity.entity.UserEntity;
import com.rajat.springsecurity.exception.UserAlreadyExistsException;
import com.rajat.springsecurity.mapper.UserMapper;
import com.rajat.springsecurity.model.UserLoginDTO;
import com.rajat.springsecurity.model.UserRegistrationDTO;
import com.rajat.springsecurity.model.UserResponseDTO;
import com.rajat.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    public UserResponseDTO register(UserRegistrationDTO userRegistrationDTO) {
        if(userRepository.findByUsername(userRegistrationDTO.getUsername())!=null)
        {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        UserEntity userEntity = UserMapper.INSTANCE.registerDTOToEntity(userRegistrationDTO);
        return UserMapper.INSTANCE.entityToResponseDTO(userRepository.save(userEntity));
    }

    public String verify(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),userLoginDTO.getPassword()));
        if(authentication.isAuthenticated()!=true)
        {
            throw new BadCredentialsException("Bad credentials exception");
        }
        return jwtService.generateToken(userLoginDTO.getUsername());
    }
}
