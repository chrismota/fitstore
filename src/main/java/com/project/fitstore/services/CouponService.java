package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.*;
import com.project.fitstore.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {
    final CouponRepository couponRepository;

    public GetAllCouponsResponse getAllCoupons(){
        return GetAllCouponsResponse.from(couponRepository.findAll());
    }

    public GetCouponResponse getCoupon(UUID id){
        return GetCouponResponse.from(findCouponById(id));
    }

    public CreateCouponResponse createCoupon(CreateCouponRequest createCouponRequest){
        return CreateCouponResponse.from(couponRepository.save(createCouponRequest.toCoupon()));
    }

    public UpdateCouponResponse updateCoupon(UUID id, UpdateCouponRequest updateCouponRequest){
        Coupon coupon = findCouponById(id);

        coupon.setName(updateCouponRequest.name());
        coupon.setStartTime(updateCouponRequest.startTime());
        coupon.setExpirationTime(updateCouponRequest.expirationTime());
        coupon.setPercentage(updateCouponRequest.percentage());
        coupon.setMinValue(updateCouponRequest.minValue());
        coupon.setUpdatedAt(LocalDateTime.now());

        return UpdateCouponResponse.from(couponRepository.save(coupon));
    }

    public void deleteCoupon(UUID id){
        couponRepository.delete(findCouponById(id));
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
