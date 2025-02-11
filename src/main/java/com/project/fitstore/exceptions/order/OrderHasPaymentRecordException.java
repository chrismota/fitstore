package com.project.fitstore.exceptions.order;

import org.springframework.dao.DataIntegrityViolationException;

public class OrderHasPaymentRecordException extends DataIntegrityViolationException {
    public OrderHasPaymentRecordException() {
        super("Could not delete order because a attempt was made to pay it before.");
    }

    public OrderHasPaymentRecordException(String message) {
        super(message);
    }
}
