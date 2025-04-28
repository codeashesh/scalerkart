package com.scalerkart.ecommerce.services.authentication.model.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}
