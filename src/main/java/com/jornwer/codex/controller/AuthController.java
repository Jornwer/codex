package com.jornwer.codex.controller;

import com.jornwer.codex.dto.AuthenticationResponse;
import com.jornwer.codex.dto.LoginRequest;
import com.jornwer.codex.dto.UserPayload;
import com.jornwer.codex.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @ApiOperation(value = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User registered"),
            @ApiResponse(code = 400, message = "Invalid credentials")
    })
    public AuthenticationResponse signup(@Valid @RequestBody UserPayload userPayload) {
        return authService.signup(userPayload);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Authorize user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User logged in"),
            @ApiResponse(code = 400, message = "Invalid credentials")
    })
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
