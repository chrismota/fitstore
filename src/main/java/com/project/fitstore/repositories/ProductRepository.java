package com.project.fitstore.repositories;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    Optional<Product> findProductBySku(String sku);

    Optional<Product> findProductByImagePath(String image);

    List<Product> findByIdIn(List<Long> productIds);
}
