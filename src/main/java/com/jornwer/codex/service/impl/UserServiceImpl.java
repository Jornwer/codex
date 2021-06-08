package com.jornwer.codex.service.impl;

import com.jornwer.codex.exception.NotFoundException;
import com.jornwer.codex.model.Role;
import com.jornwer.codex.model.User;
import com.jornwer.codex.repository.UserRepository;
import com.jornwer.codex.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        User user = User.builder()
                .username("admin")
                .email("admin@mail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
