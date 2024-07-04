package com.project.fitstore.domain.order;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private BigDecimal aggregatedValue;
    private BigDecimal discountValue;
    private BigDecimal finalValue;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
