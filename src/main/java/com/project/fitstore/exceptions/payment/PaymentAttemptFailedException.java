package com.project.fitstore.exceptions.payment;

public class PaymentAttemptFailedException extends RuntimeException {
    public PaymentAttemptFailedException() {
        super("There was an error on payment attempt. Please, try again later.");
    }

    public PaymentAttemptFailedException(String message) {
        super(message);
    }
}
