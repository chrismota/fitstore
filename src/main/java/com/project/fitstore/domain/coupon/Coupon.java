package com.project.fitstore.domain.coupon;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    @Column(unique=true)
    private String code;
    private Double percentage;
    private LocalDateTime startTime;
    private LocalDateTime expirationTime;
    private BigDecimal minValue;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
