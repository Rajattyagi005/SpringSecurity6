package com.rajat.springsecurity.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.springsecurity.model.GlobalExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", "Authentication failed: " + exception.getMessage());

        GlobalExceptionResponseDTO globalExceptionResponseDTO = new GlobalExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),  // 401 Unauthorized
                errorDetails
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(globalExceptionResponseDTO));
    }
}
