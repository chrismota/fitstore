package com.project.fitstore.services;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.dtos.payment.CreatePaymentDto;
import com.project.fitstore.dtos.payment.PaymentListResponseDto;
import com.project.fitstore.dtos.payment.PaymentResponseDto;
import com.project.fitstore.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final OrderService orderService;

    public PaymentListResponseDto getAllPayments(){
        return PaymentListResponseDto.from(paymentRepository.findAll());
    }
    public PaymentResponseDto getPayment(UUID id){
        return PaymentResponseDto.from(findPaymentById(id));
    }
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

    public void deletePayment(UUID id){
        paymentRepository.delete(findPaymentById(id));
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

    private Payment findPaymentById(UUID id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        }
        throw new RuntimeException("Payment not found");
    }


}
