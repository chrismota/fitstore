package com.project.fitstore.services;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.dtos.payment.CreatePaymentDto;
import com.project.fitstore.dtos.payment.PaymentResponseDto;
import com.project.fitstore.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final OrderService orderService;

    @Transactional
    public PaymentResponseDto createPayment(CreatePaymentDto createPaymentDto) {
        Order order = orderService.findOrderById(createPaymentDto.orderId());

        checkIfOrderIsValid(order);
        checkIfOrderIsExpired(order);

        Payment payment = createPaymentDto.toPayment();

        order.setStatus(Status.PAID);
        order.setUpdatedAt(LocalDateTime.now());
        orderService.saveOrder(order);

        return PaymentResponseDto.from(paymentRepository.save(payment));
    }

    private void checkIfOrderIsValid(Order order) {
        if (order.getStatus() != Status.CREATED) {
            throw new RuntimeException("The order is not valid anymore.");
        }
    }

    private void checkIfOrderIsExpired(Order order) {
        if (order.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("This order has already expired.");
        }
    }


}
