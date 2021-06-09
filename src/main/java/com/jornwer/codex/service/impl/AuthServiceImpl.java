package com.jornwer.codex.service.impl;

import com.jornwer.codex.dto.AuthenticationResponse;
import com.jornwer.codex.dto.LoginRequest;
import com.jornwer.codex.dto.UserPayload;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.model.Role;
import com.jornwer.codex.model.User;
import com.jornwer.codex.repository.UserRepository;
import com.jornwer.codex.security.JwtTokenProvider;
import com.jornwer.codex.service.AuthService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signup(@NotNull UserPayload request) {
        isUserRegistered(request);

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        user = userRepository.save(user);

        return generateAuthenticationToken(request.getUsername(), user.getRole());
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userRepository.findByUsername(loginRequest.getUsername()).get();
        Role role = user.getRole();
        return generateAuthenticationToken(user.getUsername(), role);
    }

    private void isUserRegistered(UserPayload payload) throws DuplicateException {
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new DuplicateException("User with this username already exists");
        }
        if (userRepository.findByEmail(payload.getEmail()).isPresent()) {
            throw new DuplicateException("User with this email already exists");
        }
    }

    private AuthenticationResponse generateAuthenticationToken(String username, Role role) {
        String token = jwtTokenProvider.createToken(username);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .username(username)
                .role(role)
                .build();
    }
}
