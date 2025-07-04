package com.project.fitstore.exceptions.coupon;

public class DuplicateCouponCodeException extends RuntimeException {
    public DuplicateCouponCodeException() {
        super("Coupon with this code already exists.");
    }

    public DuplicateCouponCodeException(String message) {
        super(message);
    }
}
