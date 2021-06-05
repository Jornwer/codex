package com.jornwer.codex.controller;

import com.jornwer.codex.dto.AuthenticationResponse;
import com.jornwer.codex.dto.LoginRequest;
import com.jornwer.codex.dto.UserPayload;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public AuthenticationResponse signup(@Valid @RequestBody UserPayload userPayload) throws DuplicateException {
        return authService.signup(userPayload);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
