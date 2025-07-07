package com.project.fitstore.exceptions.coupon;

public class CouponNotAttendsMinValueException extends RuntimeException {
    public CouponNotAttendsMinValueException() {
        super("One or more coupons does not attend the minimum value for this order.");
    }

    public CouponNotAttendsMinValueException(String message) {
        super(message);
    }
}
