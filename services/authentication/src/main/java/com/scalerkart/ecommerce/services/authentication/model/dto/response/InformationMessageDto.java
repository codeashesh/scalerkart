package com.scalerkart.ecommerce.services.authentication.model.dto.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationMessageDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private String age;
    private String phone;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

}
