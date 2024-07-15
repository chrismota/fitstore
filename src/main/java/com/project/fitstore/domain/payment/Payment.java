package com.project.fitstore.domain.payment;

import com.project.fitstore.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Method method;

    @ManyToOne
    private Order order;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
