package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.CouponResponseDto;
import com.project.fitstore.dtos.coupon.CreateCouponDto;
import com.project.fitstore.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {
    final CouponRepository couponRepository;

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

    public CouponResponseDto getCoupon(UUID id){
        return CouponResponseDto.from(findCouponById(id));
    }
    public Coupon createCoupon(CreateCouponDto createCouponDto){
        return couponRepository.save(createCouponDto.toCoupon());
    }

    public Coupon findCouponById(UUID id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            return coupon.get();
        }
        throw new RuntimeException("Coupon not found");
    }
    public List<Coupon> findCouponsByIds(List<UUID> ids) {
        return couponRepository.findByIdIn(ids);
    }
}
