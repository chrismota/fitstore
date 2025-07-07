package com.project.fitstore.exceptions.coupon;

public class CouponUnexpectedPercentageException extends RuntimeException {
    public CouponUnexpectedPercentageException() {
        super("Discount cannot be greater than a hundred percent");
    }

    public CouponUnexpectedPercentageException(String message) {
        super(message);
    }
}
