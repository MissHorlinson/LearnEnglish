package com.example.demo.exception;

public class UserDeleteException extends RuntimeException{

    public UserDeleteException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
