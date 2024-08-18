package com.rajat.springsecurity.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> fieldErrors;
}
