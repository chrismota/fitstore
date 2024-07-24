package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.SubCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotNull(message = "brand is mandatory")
        @NotBlank(message = "brand cannot be blank")
        String brand,
        Category category,
        SubCategory subCategory,

        @NotNull(message = "price is mandatory")
        BigDecimal price){
}
