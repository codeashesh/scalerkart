package com.scalerkart.ecommerce.services.authentication.api;

import com.scalerkart.ecommerce.services.authentication.model.dto.request.UserDto;
import com.scalerkart.ecommerce.services.authentication.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
@Api(value = "User API", tags = "Operations related to users")
public class UserManagerController {

    @Autowired
    private final UserAuthService authService;

    @ApiOperation(value = "Get user by ID", notes = "Retrieve user information based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User retrieved successfully", response = UserDto.class),
            @ApiResponse(code = 404, message = "User not found", response = ResponseEntity.class)
    })
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') and principal.id == #id")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authService.findById(id), HttpStatus.OK);
    }
}
