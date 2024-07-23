package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;

import java.util.List;
import java.util.UUID;

public record GetPaymentResponse
        (UUID id,
         Method method, Status status,
         GetPaymentOrderResponse order,
         List<GetPaymentCouponResponse> coupons) {
    public static GetPaymentResponse from(Payment payment) {
        return new GetPaymentResponse(payment.getId(), payment.getMethod(), payment.getStatus(),
                GetPaymentOrderResponse.from(payment.getOrder()),
                payment.getCoupons().stream().map(GetPaymentCouponResponse::from).toList());
    }

}

