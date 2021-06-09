package com.jornwer.codex.service;

import com.jornwer.codex.dto.LoginRequest;
import com.jornwer.codex.dto.UserPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    private UserPayload payload;
    private LoginRequest request;

    @BeforeEach
    public void setup() {
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString();

        payload = new UserPayload(username, password, email);
        request = new LoginRequest(username, password);
    }

    @Test
    public void shouldThrowErrorIfUserUnregistered() {
        assertThrows(BadCredentialsException.class, () -> authService.login(request));
    }

    @Test
    public void shouldNotThrowErrorIfUserRegistered() {
        authService.signup(payload);
        authService.login(request);
    }
}
