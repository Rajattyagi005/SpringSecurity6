package com.rajat.springsecurity.controller;

import com.rajat.springsecurity.model.AuthenticationTokenResponseDTO;
import com.rajat.springsecurity.model.UserLoginDTO;
import com.rajat.springsecurity.model.UserRegistrationDTO;
import com.rajat.springsecurity.model.UserResponseDTO;
import com.rajat.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO)
    {
        return new ResponseEntity<>(userService.register(userRegistrationDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationTokenResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO)
    {
        String token = userService.verify(userLoginDTO);
        AuthenticationTokenResponseDTO authenticationTokenResponseDTO = new AuthenticationTokenResponseDTO(token, System.currentTimeMillis(), System.currentTimeMillis() +60*60*1000);
        return new ResponseEntity<>(authenticationTokenResponseDTO, HttpStatus.OK);
    }
}
