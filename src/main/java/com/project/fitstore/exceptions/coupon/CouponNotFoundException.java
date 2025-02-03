package com.project.fitstore.exceptions.coupon;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException() {
        super("Coupon not found");
    }
    public CouponNotFoundException(String message) {
        super(message);
    }
}
