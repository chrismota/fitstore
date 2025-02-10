package com.project.fitstore.domain.coupon;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupons_seq")
    @SequenceGenerator(name = "coupons_seq", sequenceName = "coupons_seq", allocationSize = 1)
    private Long id;

    private String name;
    @Column(unique = true)
    private String code;
    private Double percentage;
    private LocalDateTime startTime;
    private LocalDateTime expirationTime;
    private BigDecimal minValue;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
