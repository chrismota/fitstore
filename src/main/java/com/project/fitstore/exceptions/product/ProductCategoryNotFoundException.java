package com.project.fitstore.exceptions.product;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException() {
        super("Product category not found");
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
