package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;

public record UpdateProductResponse(Long id, String name, String description, String brand, String sku,
                                    Category category, SubCategory subCategory, BigDecimal price, String imagePath) {
    public static UpdateProductResponse from(Product product) {
        return new UpdateProductResponse(product.getId(), product.getName(), product.getDescription(), product.getBrand(),
                product.getSku(), product.getCategory(), product.getSubCategory(), product.getPrice(),
                product.getImagePath());
    }
}
