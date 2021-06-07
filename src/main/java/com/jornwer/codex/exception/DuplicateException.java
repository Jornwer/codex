package com.jornwer.codex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DuplicateException extends Exception {
    public DuplicateException(String s) {
        super(s);
    }
}
