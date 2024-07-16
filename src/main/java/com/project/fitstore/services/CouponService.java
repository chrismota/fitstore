package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.CreateCouponDto;
import com.project.fitstore.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    final CouponRepository couponRepository;
    final PaymentService paymentService;
    public Coupon createCoupon(CreateCouponDto createCouponDto){
        paymentService.findPaymentById(createCouponDto.paymentId());
        return couponRepository.save(createCouponDto.toCoupon());
    }
}
