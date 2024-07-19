package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.dtos.payment.CreateCouponListForPaymentDto;
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
    final CouponService couponService;

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

        if(createPaymentDto.coupons() != null){
            List<Coupon> couponList = couponService.findCouponsByIds(createPaymentDto.coupons().stream().map(CreateCouponListForPaymentDto::id).toList());
            checkIfCouponsAreValid(createPaymentDto.coupons(), couponList);
            payment.setCoupons(couponList);
        }

        order.setStatus(Status.PAID);
        order.setUpdatedAt(LocalDateTime.now());
        orderService.saveOrder(order);

        return PaymentResponseDto.from(paymentRepository.save(payment), order);
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
    private void checkIfCouponsAreValid(List<CreateCouponListForPaymentDto> couponsIds, List<Coupon> couponList) {

        for (var couponId: couponsIds) {
            var coupon = couponList.stream().filter(couponEntity -> couponEntity.getId().equals(couponId.id())).findFirst();
            if(coupon.isEmpty())
                throw new RuntimeException("coupon does not exist");

            checkIfCouponIsActive(coupon.get());
        }
    }

    private void checkIfCouponIsActive(Coupon coupon){
        var now = LocalDateTime.now();
        if (coupon.getExpirationTime().isBefore(now) || coupon.getStartTime().isAfter(now)){
            throw new RuntimeException("Coupon is not valid");
        }
    }

    public Payment findPaymentById(UUID id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        }
        throw new RuntimeException("Payment not found");
    }

}
