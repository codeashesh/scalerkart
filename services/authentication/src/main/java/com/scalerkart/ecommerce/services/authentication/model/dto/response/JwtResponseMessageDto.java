package com.scalerkart.ecommerce.services.authentication.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseMessageDto {
    private String accessToken;
    private String refreshToken;
    private InformationMessageDto userInfo;
}
