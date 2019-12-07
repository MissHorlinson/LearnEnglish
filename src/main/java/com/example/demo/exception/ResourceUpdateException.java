package com.example.demo.exception;

public class ResourceUpdateException extends RuntimeException {

    public ResourceUpdateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
