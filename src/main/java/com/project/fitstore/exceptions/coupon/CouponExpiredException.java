package com.project.fitstore.exceptions.coupon;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException() {
        super("Coupon is expired");
    }

    public CouponExpiredException(String message) {
        super(message);
    }
}
