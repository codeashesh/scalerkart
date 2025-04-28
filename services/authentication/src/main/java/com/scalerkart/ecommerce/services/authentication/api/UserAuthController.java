package com.scalerkart.ecommerce.services.authentication.api;

import com.scalerkart.ecommerce.services.authentication.model.dto.request.LoginRequestDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.request.UserDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.GenericResponseDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.InformationMessageDto;
import com.scalerkart.ecommerce.services.authentication.model.dto.response.JwtResponseMessageDto;
import com.scalerkart.ecommerce.services.authentication.security.validate.AuthorityTokenUtil;
import com.scalerkart.ecommerce.services.authentication.security.validate.TokenValidate;
import com.scalerkart.ecommerce.services.authentication.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@SuppressWarnings("unused")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-auth")
@Api(value = "User Authentication API", tags = "APIs for user registration, login, and authentication")
public class UserAuthController {

    @Autowired
    private final UserAuthService authService;

    @Autowired
    private final TokenValidate tokenValidate;

    @ApiOperation(value = "Register a new user", notes = "Registers a new user with the provided details.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created successfully", response = GenericResponseDto.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponseDto.class)
    })
    @PostMapping("/register")
    public Mono<GenericResponseDto> register(@Valid @RequestBody UserDto userDto) {
        return authService.register(userDto)
                .map(user -> new GenericResponseDto("User: " + userDto.getUsername() + " registered successfully."))
                .onErrorResume(error -> Mono.just(new GenericResponseDto("Error occurred while registering.")));
    }

    @ApiOperation(value = "User login", notes = "Logs in a user with the provided credentials.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Login successful", response = JwtResponseMessageDto.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseEntity.class)
    })
    @PostMapping("/login")
    public Mono<ResponseEntity<JwtResponseMessageDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    JwtResponseMessageDto errorjwtResponseMessage = new JwtResponseMessageDto(
                            null,
                            null,
                            new InformationMessageDto()
                    );
                    return Mono.just(new ResponseEntity<>(errorjwtResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }

    @ApiOperation(value = "User logout", notes = "Logs out the authenticated user.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Logged out successfully", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class)
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String authorizationToken) {
        log.info("Logout endpoint called");
        authService.logout(authorizationToken.substring(7));
        return ResponseEntity.ok("Logout Successful");
    }

    @ApiOperation(value = "Validate JWT token", notes = "Validates the provided JWT token.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token is valid", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = GenericResponseDto.class)
    })
    @GetMapping("/validate-token")
    public ResponseEntity<GenericResponseDto> validateToken(@RequestHeader(name = "Authorization") String authorizationToken) {
        if (tokenValidate.validateToken(authorizationToken)) {
            return ResponseEntity.ok(new GenericResponseDto("Valid token"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GenericResponseDto("Invalid token"));
        }
    }

    @ApiOperation(value = "Refresh JWT token", notes = "Refresh the provided JWT token.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token is valid", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = GenericResponseDto.class)
    })
    @GetMapping("/refresh-token")
    public ResponseEntity<GenericResponseDto> refreshToken() {
        return ResponseEntity.ok(new GenericResponseDto("Valid token"));
    }

    @ApiOperation(value = "Check user authority", notes = "Checks if the user has the specified authority.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Role access API", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = GenericResponseDto.class)
    })
    @GetMapping("/hasAuthority")
    public ResponseEntity<GenericResponseDto> getAuthority(@RequestHeader(name = "Authorization") String authorizationToken,
                                                           @RequestParam(name = "role") String requiredRole) {
        AuthorityTokenUtil authorityTokenUtil = new AuthorityTokenUtil();
        List<String> authorities = authorityTokenUtil.checkPermission(authorizationToken);
        if (authorities.contains(requiredRole)) {
            return ResponseEntity.ok(new GenericResponseDto("Role access api"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponseDto("Invalid token"));
        }
    }
}
