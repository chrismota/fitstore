package com.project.fitstore.repositories;

import com.project.fitstore.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query("select p from Payment p where p.order.id = ?1 and p.order.customer.id = ?2")
    List<Payment> findPaymentsByOrderId(UUID orderId, UUID customerId);
}
