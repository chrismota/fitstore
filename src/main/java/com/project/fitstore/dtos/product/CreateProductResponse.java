package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;

public record CreateProductResponse(Long id, String name, String description, String brand, Category category,
                                    SubCategory subCategory, BigDecimal price, String imagePath) {
    public static CreateProductResponse from(Product product) {
        return new CreateProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getBrand(), product.getCategory(), product.getSubCategory(), product.getPrice(), product.getImagePath());
    }
}
