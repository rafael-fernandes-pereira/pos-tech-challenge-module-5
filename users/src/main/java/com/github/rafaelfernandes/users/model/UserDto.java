package com.github.rafaelfernandes.users.model;

import com.github.rafaelfernandes.users.entity.UserEntity;
import com.github.rafaelfernandes.users.enums.UserRoles;

public record UserDto(
    Long id,
    String name,
    String email,
    UserRoles role
) {

    public static UserDto fromEntity(UserEntity entity){
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail(), entity.getRole());
    }

} 
