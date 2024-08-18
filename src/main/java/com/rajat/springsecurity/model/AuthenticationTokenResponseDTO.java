package com.rajat.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationTokenResponseDTO {
    private String token;
    private long issuedAt;
    private long expiration;
}
