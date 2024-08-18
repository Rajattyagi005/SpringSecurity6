package com.rajat.springsecurity.exception;

import com.rajat.springsecurity.model.GlobalExceptionResponseDTO;
import com.rajat.springsecurity.model.ValidationExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationExceptionResponseDTO exceptionResponseDTO = new ValidationExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleUserAlreadyExistsExceptions(UserAlreadyExistsException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", ex.getMessage());

        GlobalExceptionResponseDTO globalExceptionResponseDTO = new GlobalExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errorDetails
        );
        return new ResponseEntity<>(globalExceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleUserNotFoundException(Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", ex.getMessage());

        GlobalExceptionResponseDTO globalExceptionResponseDTO = new GlobalExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorDetails
        );
        return new ResponseEntity<>(globalExceptionResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
