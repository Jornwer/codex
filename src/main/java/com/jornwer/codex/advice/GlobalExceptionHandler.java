package com.jornwer.codex.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setStatus(HttpStatus.FORBIDDEN);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
