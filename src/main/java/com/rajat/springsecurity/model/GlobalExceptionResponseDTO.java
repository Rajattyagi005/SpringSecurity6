package com.rajat.springsecurity.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalExceptionResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private Map<String, String> errorDetails;
}
