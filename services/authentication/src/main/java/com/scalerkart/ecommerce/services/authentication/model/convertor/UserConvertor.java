package com.scalerkart.ecommerce.services.authentication.model.convertor;

import com.scalerkart.ecommerce.services.authentication.model.dto.request.UserDto;
import com.scalerkart.ecommerce.services.authentication.model.entity.User;

import java.util.stream.Collectors;

public interface UserConvertor {

    static UserDto toDto(final User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .gender(user.getGender())
                .age(user.getAge())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(role -> role.name().name()).collect(Collectors.toSet()))
                .build();
    }

    static User fromDto(final UserDto userDto) {
        if (userDto == null) return null;
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .age(userDto.getAge())
                .phone(userDto.getPhone())
                .avatar(userDto.getAvatar())
                .build();
    }
}
