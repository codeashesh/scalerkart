package com.scalerkart.ecommerce.services.order.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "The firstName must not be left blank")
    @Size(min = 3, max = 20, message = "The firstName must be 6 characters or more")
    private String firstName;

    @NotBlank(message = "The lastName must not be left blank")
    @Size(min = 2, max = 20, message = "The lastName must be 6 characters or more")
    private String lastName;

    @NotBlank(message = "The username must not be left blank")
    @Size(min = 6, max = 20, message = "The username must be 6 characters or more")
    private String username;

    @Size(min = 8, max = 16, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain all uppercase and lowercase letters and numbers")
    private String password;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender must not be left blank")
    private String gender;

    @NotBlank(message = "Age must not be left blank")
    private String age;

//    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 digits")
    private String phone;

    @Pattern(regexp = "^(http|https)://.*$", message = "Avatar URL must be a valid HTTP or HTTPS URL")
    private String avatar;

    private Set<String> roles;
}
