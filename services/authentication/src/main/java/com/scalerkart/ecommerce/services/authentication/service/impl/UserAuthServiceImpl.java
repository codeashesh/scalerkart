package com.scalerkart.ecommerce.services.authentication.service.impl;

import com.scalerkart.ecommerce.services.authentication.exception.wrapper.*;
import com.scalerkart.ecommerce.services.authentication.model.convertor.UserConvertor;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.UserDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.ChangePasswordRequestDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.LoginRequestDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.InformationMessageDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.JwtResponseMessageDto;
import com.scalerkart.ecommerce.services.authentication.model.entity.Role;
import com.scalerkart.ecommerce.services.authentication.model.entity.RoleName;
import com.scalerkart.ecommerce.services.authentication.model.entity.User;
import com.scalerkart.ecommerce.services.authentication.repository.UserRepository;
import com.scalerkart.ecommerce.services.authentication.security.jwt.JwtProvider;
import com.scalerkart.ecommerce.services.authentication.security.userprinciple.UserDetailService;
import com.scalerkart.ecommerce.services.authentication.security.userprinciple.UserPrinciple;
import com.scalerkart.ecommerce.services.authentication.service.UserAuthService;
import com.scalerkart.ecommerce.services.authentication.service.UserRoleService;
import com.scalerkart.ecommerce.services.authentication.exception.wrapper.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailService userDetailsService;
    @Autowired
    UserRoleService roleService;

    @Override
    public Mono<User> register(final UserDto userDto) {
        return Mono.defer(() -> {
            var validationErrors = validateRegistrationRequest(userDto);
            if (validationErrors != null) return validationErrors;
            var user = UserConvertor.fromDto(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(getUserRoles(userDto));
            userRepository.save(user);
            return Mono.just(user);
        });
    }

    @Override
    public Mono<JwtResponseMessageDto> login(final LoginRequestDto loginRequestDto) {
        return Mono.fromCallable(() -> {
            var userDetails = getUserDetails(loginRequestDto);
            validateLoginRequest(loginRequestDto, userDetails);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    loginRequestDto.getPassword(),
                    userDetails.getAuthorities());
            String accessToken = jwtProvider.createToken(authentication);
            String refreshToken = jwtProvider.createRefreshToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserPrinciple userPrinciple = (UserPrinciple) userDetails;
            return JwtResponseMessageDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .userInfo(InformationMessageDto.builder()
                            .id(userPrinciple.id())
                            .firstName(userPrinciple.firstName())
//                            .lastName(userPrinciple.getLastName())
                            .username(userPrinciple.username())
                            .email(userPrinciple.email())
                            .phone(userPrinciple.phone())
                            .gender(userPrinciple.gender())
//                            .age(userPrinciple.getAge())
                            .avatar(userPrinciple.avatar())
                            .roles(userPrinciple.roles())
                            .build())
                    .build();
        }).onErrorResume(Mono::error);
    }

    @Override
    public void logout(String token) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(null);
            if (authentication != null && authentication.isAuthenticated()) {
                // Invalidate the current token by reducing its expiration time
                jwtProvider.reduceTokenExpiration(token);
            }
            SecurityContextHolder.clearContext();
        } catch (Exception ex) {
            log.error("Error occurred during logout with exception: "+ ex.getMessage());
        }
    }

    @Transactional
    @Override
    public Mono<String> changePassword(final ChangePasswordRequestDto request) {
        try {
            UserDetails userDetails = getCurrentUserDetails();
            String username = userDetails.getUsername();
            User existingUser = findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username " + username));
            if (passwordEncoder.matches(request.getOldPassword(), userDetails.getPassword())) {
                if (validateNewPassword(request.getNewPassword(), request.getConfirmPassword())) {
                    existingUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(existingUser);
                }
                return Mono.just("Password changed failed.");
            } else {
                return Mono.error(new PasswordNotFoundException("Incorrect password"));
            }
        } catch (Exception e) {
            return Mono.error(new UserNotAuthenticatedException("Transaction silently rolled back"));
        }
    }

    @Override
    public UserDto findById(Long userId) {
        return userRepository.findById(userId)
                .map(UserConvertor::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId: " + userId));
    }

    public Optional<User> findByUsername(final String userName) {
        return Optional.ofNullable(userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found with userName: " + userName)));
    }

    private Mono<User> validateRegistrationRequest(final UserDto userDto) {
        if (existsByUsername(userDto.getUsername())) {
            return Mono.error(new EmailOrUsernameNotFoundException("The username " + userDto.getUsername() + " is existed, please try again."));
        }
        if (existsByEmail(userDto.getEmail())) {
            return Mono.error(new EmailOrUsernameNotFoundException("The email " + userDto.getEmail() + " is existed, please try again."));
        }
        if (existsByPhoneNumber(userDto.getPhone())) {
            return Mono.error(new PhoneNumberNotFoundException("The phone number " + userDto.getPhone() + " is existed, please try again."));
        }
        return null;
    }

    private Set<Role> getUserRoles(final UserDto userDto) {
        if(CollectionUtils.isEmpty(userDto.getRoles())) {
            return Set.of(new Role(2L, RoleName.USER));
        }
        return userDto.getRoles()
                .stream()
                .map(role -> roleService.findByName(mapToRoleName(role))
                        .orElse(new Role(2L, RoleName.USER)))
                .collect(Collectors.toSet());
    }

    private void validateLoginRequest(final LoginRequestDto loginRequestDto, final UserDetails userDetails) {
        // check username
        if (userDetails == null) {
            throw new UserNotFoundException("User not found");
        }
        // Check password
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userDetails.getPassword())) {
            throw new PasswordNotFoundException("Incorrect password");
        }
    }

    private UserDetails getUserDetails(final LoginRequestDto loginRequestDto) {
        String usernameOrEmail = loginRequestDto.getUsername();
        boolean isEmail = usernameOrEmail.contains("@");

        UserDetails userDetails;
        if (isEmail) {
            userDetails = userDetailsService.loadUserByEmail(usernameOrEmail);
        } else {
            userDetails = userDetailsService.loadUserByUsername(usernameOrEmail);
        }
        return userDetails;
    }

    private boolean validateNewPassword(final String newPassword, final String confirmPassword) {
        return Objects.equals(newPassword, confirmPassword);
    }

    private RoleName mapToRoleName(final String roleName) {
        return switch (roleName.toLowerCase()) {
            case "admin" -> RoleName.ADMIN;
            case "user" -> RoleName.USER;
            default -> null;
        };
    }

    private UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        } else {
            throw new UserNotAuthenticatedException("User not authenticated.");
        }
    }

    private String getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object credentials = authentication.getCredentials();
            if (credentials instanceof String) {
                return (String) credentials;
            }
        }
        return null;
    }

    public boolean existsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(final String phone) {
        return userRepository.existsByPhoneNumber(phone);
    }
}
