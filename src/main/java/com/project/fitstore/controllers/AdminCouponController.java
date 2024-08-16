package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.*;
import com.project.fitstore.services.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponController {
    final CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }

    @PutMapping("/coupons/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCoupon(@PathVariable("id") UUID id, @RequestBody @Valid UpdateCouponRequest updateCouponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updateCouponRequest));
    }

    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<Void> updateCoupon(@PathVariable("id") UUID id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}


