package com.example.userbehaviormanagement.services.exceptions.errors;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
