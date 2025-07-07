package com.project.fitstore.exceptions.customer;

public class DuplicateCustomerFieldException extends RuntimeException {
    public DuplicateCustomerFieldException() {
        super("One or more fields are already in use. Please check your data and try again.");
    }

    public DuplicateCustomerFieldException(String message) {
        super(message);
    }
}
