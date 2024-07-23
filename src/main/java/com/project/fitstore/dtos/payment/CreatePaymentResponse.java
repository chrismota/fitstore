package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;

import java.util.List;
import java.util.UUID;

public record CreatePaymentResponse(UUID id,
                                    Method method, Status status,
                                    CreatePaymentOrderResponse order,
                                    List<CreatePaymentCouponResponse> coupons) {

    public static CreatePaymentResponse from(Payment payment, Order order){
        return new CreatePaymentResponse(payment.getId(), payment.getMethod(), payment.getStatus(),
                CreatePaymentOrderResponse.from(order),
                payment.getCoupons().stream().map(CreatePaymentCouponResponse::from).toList());
    }
}

