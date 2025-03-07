package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.dtos.payment.CreatePaymentRequest;
import com.project.fitstore.dtos.payment.CreatePaymentResponse;
import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.dtos.payment.GetPaymentResponse;
import com.project.fitstore.exceptions.payment.PaymentAttemptFailedException;
import com.project.fitstore.exceptions.payment.PaymentNotFoundException;
import com.project.fitstore.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final OrderService orderService;
    final CouponService couponService;

    public GetAllPaymentsResponse getAllPaymentsFromOrder(UUID orderId, UUID customerId) {
        return GetAllPaymentsResponse.from(paymentRepository.findPaymentsByOrderId(orderId, customerId));
    }

    public GetAllPaymentsResponse getAllPayments() {
        return GetAllPaymentsResponse.from(paymentRepository.findAll());
    }

    public GetPaymentResponse getPayment(UUID id) {
        return GetPaymentResponse.from(findPaymentById(id));
    }

    @Transactional
    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest, UUID customerId) {
        Order order = orderService.findOrderByIdAndCustomerId(createPaymentRequest.orderId(), customerId);
        orderService.checkIfOrderIsExpired(order);
        orderService.checkIfOrderIsValid(order);

        Payment payment = createPaymentRequest.toPayment();

        List<Coupon> couponList = new ArrayList<>();

        if (createPaymentRequest.coupons() != null) {
            couponList = couponService.getCouponList(createPaymentRequest);
            couponService.checkIfCouponsAreValid(createPaymentRequest.coupons(), couponList, order);
            payment.setCoupons(couponList);
        }

        try {
            payOrder(order, couponList, payment);
        } catch (RuntimeException e) {
            payment.setStatus(com.project.fitstore.domain.payment.Status.FAILED);
            throw new PaymentAttemptFailedException();
        }

        return CreatePaymentResponse.from(paymentRepository.save(payment), order);
    }

    public void deletePayment(UUID id) {
        paymentRepository.delete(findPaymentById(id));
    }

    private void payOrder(Order order, List<Coupon> couponList, Payment payment) {
        if (!couponList.isEmpty()) {
            var discountValue = getDiscountValue(couponList, order);
            order.setDiscount(discountValue);
            order.setValueAfterDiscount(order.getValueAfterDiscount().subtract(discountValue));
        }

        order.setStatus(Status.PAID);
        order.setUpdatedAt(LocalDateTime.now());
        orderService.saveOrder(order);

        payment.setStatus(com.project.fitstore.domain.payment.Status.SUCCESS);
    }

    private BigDecimal getDiscountValue(List<Coupon> couponList, Order order) {
        double totalDiscount = 0;
        for (var coupon : couponList) {
            totalDiscount += coupon.getPercentage();
        }
        var discountValue = new BigDecimal(totalDiscount).divide(new BigDecimal(100), 2, RoundingMode.CEILING);
        return order.getFullValue().multiply(discountValue);
    }


    public Payment findPaymentById(UUID id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        }
        throw new PaymentNotFoundException();
    }

}
