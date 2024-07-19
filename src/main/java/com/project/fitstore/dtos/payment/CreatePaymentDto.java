package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public record CreatePaymentDto (UUID orderId, List<CreateCouponListForPaymentDto> coupons, Method method, Status status){
    public Payment toPayment(){
        Order order = Order.builder().id(orderId).build();

        return Payment.builder().id(null)
                .method(method)
                .order(order)
                .status(Status.CREATED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
