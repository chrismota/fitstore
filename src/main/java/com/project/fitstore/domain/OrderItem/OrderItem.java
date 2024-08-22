package com.project.fitstore.domain.OrderItem;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer quantity;
    private BigDecimal unityPrice;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
