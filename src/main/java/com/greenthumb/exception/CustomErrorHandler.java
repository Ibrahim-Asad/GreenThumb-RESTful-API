package com.greenthumb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(RequestRejectedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRequestRejectedException(RequestRejectedException ex) {
        return "Invalid request: " + ex.getMessage();
    }
}
