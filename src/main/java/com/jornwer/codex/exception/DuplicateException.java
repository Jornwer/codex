package com.jornwer.codex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateException extends Exception {
    public DuplicateException(String s) {
        super(s);
    }
}
