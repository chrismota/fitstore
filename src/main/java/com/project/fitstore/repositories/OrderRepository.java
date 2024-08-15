package com.project.fitstore.repositories;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByCustomerId(UUID customerId);

    Optional<Order> findOrderByIdAndCustomerId(UUID id, UUID customerId);
}
