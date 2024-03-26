package com.userservice2.userservice2.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice2.userservice2.models.Role;
import com.userservice2.userservice2.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
