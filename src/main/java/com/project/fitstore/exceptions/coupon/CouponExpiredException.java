package com.project.fitstore.exceptions.coupon;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException() {
        super("One or more coupons are expired.");
    }

    public CouponExpiredException(String message) {
        super(message);
    }
}
