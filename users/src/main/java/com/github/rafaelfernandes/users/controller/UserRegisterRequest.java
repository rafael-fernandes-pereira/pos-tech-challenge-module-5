package com.github.rafaelfernandes.users.controller;

import java.util.Objects;

import org.springframework.security.core.userdetails.User;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.UserRoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UserRegisterRequest(
    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    String name,
    
    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    String email,
    
    @NotNull(message = "Password is required")
    @Size(min = 10, message = "Password must be at least 10 characters long")
    String password,
    
    UserRoles role

) {

    public UserEntity toEntity(){
        return UserEntity.builder()
            .name(name)
            .password(password)
            .email(email)
            .userRoles(Objects.nonNull(role) ? role : UserRoles.CUSTOMER)
            .build();
    }

}
