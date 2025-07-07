package com.project.fitstore.exceptions.general;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException() {
        super("Invalid parameter value for status.");
    }

    public InvalidStatusException(String message) {
        super(message);
    }
}
