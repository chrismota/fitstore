package com.project.fitstore.repositories;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findCustomerByImagePath(String image);

}
