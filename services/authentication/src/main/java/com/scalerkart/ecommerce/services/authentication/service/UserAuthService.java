package com.scalerkart.ecommerce.services.authentication.service;

import com.scalerkart.ecommerce.services.authentication.model.dto.request.UserDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.ChangePasswordRequestDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.LoginRequestDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.JwtResponseMessageDto;
import com.scalerkart.ecommerce.services.authentication.model.entity.User;
import reactor.core.publisher.Mono;

public interface UserAuthService {
    Mono<User> register(UserDto userDto);
    Mono<JwtResponseMessageDto> login(LoginRequestDto loginRequestDto);
    void logout(String token);
    Mono<String> changePassword(ChangePasswordRequestDto request);
    UserDto findById(Long userId);
}
