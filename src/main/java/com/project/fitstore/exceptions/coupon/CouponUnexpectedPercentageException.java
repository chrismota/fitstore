package com.project.fitstore.exceptions.coupon;

public class CouponUnexpectedPercentageException extends RuntimeException {
    public CouponUnexpectedPercentageException() {
        super("Coupon have a unexpected percentage");
    }
    public CouponUnexpectedPercentageException(String message) {
        super(message);
    }
}
