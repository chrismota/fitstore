package com.project.fitstore.exceptions.order;

public class OrderExpiredException extends RuntimeException {
    public OrderExpiredException() {
        super("This order has already expired.");
    }

    public OrderExpiredException(String message) {
        super(message);
    }
}
