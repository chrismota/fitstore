package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import java.util.List;

public record GetAllCouponsResponse(List<GetCouponResponse> coupons) {
    public static GetAllCouponsResponse from(List<Coupon> couponList){
        return new GetAllCouponsResponse(couponList.stream().map(GetCouponResponse::from).toList());
    }
}
