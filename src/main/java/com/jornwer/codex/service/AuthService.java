package com.jornwer.codex.service;

import com.jornwer.codex.dto.AuthenticationResponse;
import com.jornwer.codex.dto.LoginRequest;
import com.jornwer.codex.dto.UserPayload;

public interface AuthService {
    AuthenticationResponse signup(UserPayload request);

    AuthenticationResponse login(LoginRequest request);
}
