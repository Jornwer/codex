package com.jornwer.codex.service;

import com.jornwer.codex.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void shouldThrowExceptionIfUserNotLoggedIn() {
        assertThrows(NotFoundException.class, () -> userService.getCurrentUser());
    }
}
