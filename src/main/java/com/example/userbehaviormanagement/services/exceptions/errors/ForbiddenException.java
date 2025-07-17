package com.example.userbehaviormanagement.services.exceptions.errors;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
