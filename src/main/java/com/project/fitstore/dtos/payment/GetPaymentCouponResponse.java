package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.coupon.Coupon;

public record GetPaymentCouponResponse(Long id, String name, Double percentage) {
    public static GetPaymentCouponResponse from(Coupon coupon) {
        return new GetPaymentCouponResponse(coupon.getId(), coupon.getName(), coupon.getPercentage());
    }
}
