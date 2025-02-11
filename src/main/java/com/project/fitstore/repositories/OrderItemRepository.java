package com.project.fitstore.repositories;

import com.project.fitstore.domain.OrderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
