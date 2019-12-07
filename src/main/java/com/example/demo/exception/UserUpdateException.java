package com.example.demo.exception;

import java.io.IOException;

public class UserUpdateException extends RuntimeException {

    public UserUpdateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
