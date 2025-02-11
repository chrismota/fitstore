package com.project.fitstore.exceptions.order;

public class OrderNotValidException extends RuntimeException {
    public OrderNotValidException() {
        super("The order is not valid anymore.");
    }

    public OrderNotValidException(String message) {
        super(message);
    }
}
