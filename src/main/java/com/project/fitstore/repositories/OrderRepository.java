package com.project.fitstore.repositories;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByStatusAndExpiresAtBefore(Status status, LocalDateTime dateNow);

    List<Order> findOrdersByCustomerIdOrderByCreatedAtDesc(UUID customerId);

    List<Order> findOrdersByStatusAndCustomerIdOrderByCreatedAtDesc(Status status, UUID customerId);

    Optional<Order> findOrderByIdAndCustomerId(UUID id, UUID customerId);
}
