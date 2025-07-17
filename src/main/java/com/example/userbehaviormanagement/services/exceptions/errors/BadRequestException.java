package com.example.userbehaviormanagement.services.exceptions.errors;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
