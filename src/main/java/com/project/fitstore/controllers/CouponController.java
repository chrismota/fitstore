package com.project.fitstore.controllers;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.CreateCouponDto;
import com.project.fitstore.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    final CouponService couponService;
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody CreateCouponDto createCouponDto){
        return new ResponseEntity<>(couponService.createCoupon(createCouponDto), HttpStatus.CREATED);
    }
}
