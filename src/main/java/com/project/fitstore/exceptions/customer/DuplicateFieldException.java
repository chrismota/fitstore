package com.project.fitstore.exceptions.customer;

public class DuplicateFieldException extends RuntimeException {
    public DuplicateFieldException() {
        super("Field already in use");
    }

    public DuplicateFieldException(String message) {
        super(message);
    }
}
