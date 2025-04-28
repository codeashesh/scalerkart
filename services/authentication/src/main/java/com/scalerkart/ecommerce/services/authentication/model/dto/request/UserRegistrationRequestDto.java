package com.scalerkart.ecommerce.services.authentication.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
public class UserRegistrationRequestDto {

    @NotBlank(message = "The firstName must not be left blank")
    private String firstName;

    private String lastName;

    @NotBlank(message = "The username must not be left blank")
    private String username;

    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender must not be left blank")
    private String gender;

    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 digits")
//    @Pattern(regexp = "^\\[0-9]{9,10}$|^0[0-9]{9,10}$", message = "The phone number is not in the correct format")
    private String phone;

    private String avatar;

    private Set<String> roles;
}
