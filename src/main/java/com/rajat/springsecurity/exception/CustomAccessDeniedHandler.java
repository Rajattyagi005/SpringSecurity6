package com.rajat.springsecurity.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.springsecurity.model.GlobalExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", "Access Denied: " + accessDeniedException.getMessage());

        GlobalExceptionResponseDTO globalExceptionResponseDTO = new GlobalExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),  // 403 Forbidden
                errorDetails
        );

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(globalExceptionResponseDTO));
    }
}
