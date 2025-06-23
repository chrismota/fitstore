package com.project.fitstore.exceptions.product;

public class ProductSubCategoryNotFoundException extends RuntimeException {
    public ProductSubCategoryNotFoundException() {
        super("Product subcategory not found");
    }

    public ProductSubCategoryNotFoundException(String message) {
        super(message);
    }
}
