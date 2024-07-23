package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductResponse(UUID id, String name, String brand, Category category,
                                 SubCategory subCategory, BigDecimal price) {
    public static GetProductResponse from(Product product) {
        return new GetProductResponse(product.getId(), product.getName(), product.getBrand(), product.getCategory(),
                product.getSubCategory(), product.getPrice());
    }
}
