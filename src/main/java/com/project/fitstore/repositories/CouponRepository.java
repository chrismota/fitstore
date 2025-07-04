package com.project.fitstore.repositories;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.coupon.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByIdIn(List<Long> couponIds);

    List<Coupon> findCouponsByStatusOrderByCreatedAtDesc(Status status);

    List<Coupon> findCouponsByStatusAndExpirationTimeBefore(Status status, LocalDateTime dateNow);
}
