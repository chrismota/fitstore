package com.project.fitstore.domain.coupon;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Payment;
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

    private String name;
    private Double percentage;
    private LocalDateTime startTime;
    private LocalDateTime expirationTime;
    private BigDecimal minValue;
    @ManyToOne
    private Payment payment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
