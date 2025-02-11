package com.project.fitstore.exceptions.coupon;

public class CouponNotAttendsMinValueException extends RuntimeException {
    public CouponNotAttendsMinValueException() {
        super("Coupon does not attend the minimum value");
    }

    public CouponNotAttendsMinValueException(String message) {
        super(message);
    }
}
