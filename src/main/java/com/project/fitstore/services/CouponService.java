package com.project.fitstore.services;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.coupon.Status;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.dtos.coupon.*;
import com.project.fitstore.dtos.payment.CreatePaymentCouponRequest;
import com.project.fitstore.dtos.payment.CreatePaymentRequest;
import com.project.fitstore.exceptions.coupon.CouponExpiredException;
import com.project.fitstore.exceptions.coupon.CouponNotAttendsMinValueException;
import com.project.fitstore.exceptions.coupon.CouponNotFoundException;
import com.project.fitstore.exceptions.coupon.CouponUnexpectedPercentageException;
import com.project.fitstore.repositories.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    final CouponRepository couponRepository;

    public GetAllCouponsResponse getAllCoupons() {
        return GetAllCouponsResponse.from(couponRepository.findAll());
    }

    public GetAllCouponsResponse getCouponsByStatus(String statusParam) {
        Status status;
        try {
            status = Status.valueOf(statusParam.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException("Invalid parameter value for status: " + statusParam);
        }
        return GetAllCouponsResponse.from(couponRepository.findCouponsByStatusOrderByCreatedAtDesc(status));
    }

    public GetCouponResponse getCoupon(Long id) {
        return GetCouponResponse.from(findCouponById(id));
    }

    public CreateCouponResponse createCoupon(CreateCouponRequest createCouponRequest) {
        return CreateCouponResponse.from(couponRepository.save(createCouponRequest.toCoupon()));
    }

    public UpdateCouponResponse updateCoupon(Long id, UpdateCouponRequest updateCouponRequest) {
        Coupon coupon = findCouponById(id);

        coupon.setName(updateCouponRequest.name());
        coupon.setCode(updateCouponRequest.code());
        coupon.setStartTime(updateCouponRequest.startTime());
        coupon.setExpirationTime(updateCouponRequest.expirationTime());
        coupon.setPercentage(updateCouponRequest.percentage());
        coupon.setMinValue(updateCouponRequest.minValue());
        coupon.setUpdatedAt(LocalDateTime.now());

        return UpdateCouponResponse.from(couponRepository.save(coupon));
    }

    public UpdateCouponResponse updateCouponStatus(UpdateCouponStatusRequest updateCouponStatusRequest, Long id) {
        Status status;
        try {
            status = Status.valueOf(updateCouponStatusRequest.status().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException("Invalid field value for status: " + updateCouponStatusRequest.status());
        }

        Coupon coupon = findCouponById(id);

        coupon.setStatus(status);
        coupon.setUpdatedAt(LocalDateTime.now());

        return UpdateCouponResponse.from(couponRepository.save(coupon));
    }

    public void deleteCoupon(Long id) {
        couponRepository.delete(findCouponById(id));
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void couponScheduler() {
        List<Coupon> coupons = couponRepository.findCouponsByStatusAndExpirationTimeBefore(Status.VALID, LocalDateTime.now());
        for (Coupon coupon : coupons) {
            coupon.setStatus(Status.INVALID);
            coupon.setUpdatedAt(LocalDateTime.now());
        }
        couponRepository.saveAll(coupons);
    }

    public Coupon findCouponById(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            return coupon.get();
        }
        throw new CouponNotFoundException();
    }

    public List<Coupon> findCouponsByIds(List<Long> ids) {
        return couponRepository.findByIdIn(ids);
    }

    public void checkIfCouponIsExpired(Coupon coupon) {
        var now = LocalDateTime.now();
        if (coupon.getExpirationTime().isBefore(now) || coupon.getStartTime().isAfter(now)) {
            throw new CouponExpiredException("One or more coupons is expired.");
        }
    }

    public void checkIfCouponAttendsMinValue(Coupon coupon, Order order) {
        if (compareTo(order.getFullValue(), coupon.getMinValue()) < 0) {
            throw new CouponNotAttendsMinValueException("One or more coupons does not attend the minimum value for this order.");
        }
    }

    private static int compareTo(BigDecimal firstValue, BigDecimal secondValue) {
        return firstValue.compareTo(secondValue);
    }

    public void checkIfCouponPercentageIsValid(double totalDiscount) {
        if (totalDiscount >= 100)
            throw new CouponUnexpectedPercentageException("Discount cannot be greater than a hundred percent");
    }

    public void checkIfCouponsAreValid(List<CreatePaymentCouponRequest> couponsIds, List<Coupon> couponList, Order order) {
        double totalDiscount = 0;
        for (var couponId : couponsIds) {

            Optional<Coupon> couponOptional = couponList.stream().filter(couponEntity -> couponEntity.getId().equals(couponId.id())).findFirst();
            if (couponOptional.isEmpty())
                throw new CouponNotFoundException("One or more coupons were not found.");

            var coupon = couponOptional.get();

            checkIfCouponIsExpired(coupon);
            checkIfCouponAttendsMinValue(coupon, order);
            totalDiscount += coupon.getPercentage();
        }
        checkIfCouponPercentageIsValid(totalDiscount);
    }


    public List<Coupon> getCouponList(CreatePaymentRequest createPaymentRequest) {
        return findCouponsByIds(createPaymentRequest.coupons().stream().map(CreatePaymentCouponRequest::id).toList());
    }

}
