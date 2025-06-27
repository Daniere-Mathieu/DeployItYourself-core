package com.deployityouself.deployityouself_core.domain.shared;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super("Invalid email: ",+ message );
    }
}