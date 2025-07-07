package com.project.fitstore.repositories;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByImagePath(String image);

    List<Product> findByIdIn(List<Long> productIds);

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsBySubCategory(SubCategory subCategory);

    List<Product> findProductsByNameContainingIgnoreCase(String name);
}
