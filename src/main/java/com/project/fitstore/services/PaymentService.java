package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.dtos.payment.*;
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
        checkIfOrderIsExpired(order);
        checkIfOrderIsValid(order);

        Payment payment = createPaymentRequest.toPayment();

        List<Coupon> couponList = new ArrayList<>();

        if (createPaymentRequest.coupons() != null) {
            couponList = getCouponList(createPaymentRequest);
            checkIfCouponsAreValid(createPaymentRequest.coupons(), couponList, order);
            payment.setCoupons(couponList);
        }

        try {
            payOrder(order, couponList, payment);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return CreatePaymentResponse.from(paymentRepository.save(payment), order);
    }

    public void deletePayment(UUID id) {
        paymentRepository.delete(findPaymentById(id));
    }

    private void payOrder(Order order, List<Coupon> couponList, Payment payment) {
        if (attemptIsSuccessful()) {
            if (!couponList.isEmpty()) {
                var discountValue = getDiscountValue(couponList, order);
                order.setDiscount(discountValue);
                order.setValueAfterDiscount(order.getValueAfterDiscount().subtract(discountValue));
            }

            order.setStatus(Status.PAID);
            order.setUpdatedAt(LocalDateTime.now());
            orderService.saveOrder(order);

            payment.setStatus(com.project.fitstore.domain.payment.Status.SUCCESS);
        } else {
            payment.setStatus(com.project.fitstore.domain.payment.Status.FAILED);
            throw new RuntimeException("There was an error on payment attempt. Please, try again later.");
        }
    }

    private boolean attemptIsSuccessful() {
        Random r = new Random();
        int number = r.nextInt(10);
        return number < 3;
    }


    private BigDecimal getDiscountValue(List<Coupon> couponList, Order order) {
        double totalDiscount = 0;
        for (var coupon : couponList) {
            totalDiscount += coupon.getPercentage();
        }
        var discountValue = new BigDecimal(totalDiscount).divide(new BigDecimal(100), 2, RoundingMode.CEILING);
        return order.getFullValue().multiply(discountValue);
    }

    private List<Coupon> getCouponList(CreatePaymentRequest createPaymentRequest) {
        return couponService.findCouponsByIds(createPaymentRequest.coupons().stream().map(CreatePaymentCouponRequest::id).toList());
    }

    private void checkIfOrderIsValid(Order order) {
        if (order.getStatus() != Status.PENDING) {
            throw new RuntimeException("The order is not valid anymore.");
        }
    }

    private void checkIfOrderIsExpired(Order order) {
        if (order.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("This order has already expired.");
        }
    }

    private void checkIfCouponsAreValid(List<CreatePaymentCouponRequest> couponsIds, List<Coupon> couponList, Order order) {

        double totalDiscount = 0;
        for (var couponId : couponsIds) {

            Optional<Coupon> couponOptional = couponList.stream().filter(couponEntity -> couponEntity.getId().equals(couponId.id())).findFirst();
            if (couponOptional.isEmpty())
                throw new RuntimeException("coupon does not exist");

            var coupon = couponOptional.get();

            checkIfCouponIsActive(coupon);
            checkIfCouponAttendsMinValue(coupon, order);
            totalDiscount += coupon.getPercentage();
        }
        checkIfCouponPercentageIsValid(totalDiscount);
    }

    private void checkIfCouponPercentageIsValid(double totalDiscount) {
        if (totalDiscount >= 100)
            throw new RuntimeException("Discount cannot be greater than a hundred percent");
    }

    private void checkIfCouponIsActive(Coupon coupon) {
        var now = LocalDateTime.now();
        if (coupon.getExpirationTime().isBefore(now) || coupon.getStartTime().isAfter(now)) {
            throw new RuntimeException("Coupon is not valid");
        }
    }


    private void checkIfCouponAttendsMinValue(Coupon coupon, Order order) {
        if (compareTo(order.getFullValue(), coupon.getMinValue()) < 0) {
            throw new RuntimeException("One or more coupons is not available for this order.");
        }
    }

    public Payment findPaymentById(UUID id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        }
        throw new RuntimeException("Payment not found");
    }

    private static int compareTo(BigDecimal firstValue, BigDecimal secondValue) {
        return firstValue.compareTo(secondValue);
    }

}
