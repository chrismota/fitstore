package com.project.fitstore.repositories;

import com.project.fitstore.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByName(String name);
    Optional<Product> findProductBySku(String sku);
}
