package com.rajat.springsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@Table(name = "users")
public class UserEntity {
    @Id
    private String username;
    private String email;
    private String password;
}