package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.CreateCouponRequest;
import com.project.fitstore.dtos.coupon.CreateCouponResponse;
import com.project.fitstore.dtos.coupon.UpdateCouponRequest;
import com.project.fitstore.dtos.coupon.UpdateCouponResponse;
import com.project.fitstore.dtos.coupon.UpdateCouponStatusRequest;
import com.project.fitstore.services.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponController {
    final CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<CreateCouponResponse> createCoupon(@Valid @RequestBody CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }

    @PutMapping("/coupons/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCoupon
            (@PathVariable("id") Long id, @Valid @RequestBody UpdateCouponRequest updateCouponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updateCouponRequest));
    }

    @PutMapping("/coupons/status/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCouponStatus(
            @Valid @RequestBody UpdateCouponStatusRequest updateCouponStatusRequest, @PathVariable("id") Long id) {
        return ResponseEntity.ok(couponService.updateCouponStatus(updateCouponStatusRequest, id));
    }
}


