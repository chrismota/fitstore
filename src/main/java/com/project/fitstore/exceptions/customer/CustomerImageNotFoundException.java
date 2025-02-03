package com.project.fitstore.exceptions.customer;

public class CustomerImageNotFoundException extends RuntimeException {
    public CustomerImageNotFoundException() {
        super("Customer image not found");
    }

    public CustomerImageNotFoundException(String message) {
        super(message);
    }
}
