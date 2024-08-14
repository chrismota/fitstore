package com.project.fitstore.domain.coupon;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    private String name;
    private Double percentage;
    private LocalDateTime startTime;
    private LocalDateTime expirationTime;
    private BigDecimal minValue;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
