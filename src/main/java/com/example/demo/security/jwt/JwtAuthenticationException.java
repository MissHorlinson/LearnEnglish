package com.example.demo.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public JwtAuthenticationException(String  message) {
        super(message);
    }
}
