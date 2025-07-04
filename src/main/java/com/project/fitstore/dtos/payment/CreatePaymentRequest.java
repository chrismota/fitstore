package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreatePaymentRequest(
        @NotNull(message = "Order ID is mandatory")
        UUID orderId,
        @UniqueElements(message = "You cannot add the same coupon twice")
        List<CreatePaymentCouponRequest> coupons,
        @NotNull(message = "Payment method is mandatory")
        Method method) {
    public Payment toPayment() {
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
