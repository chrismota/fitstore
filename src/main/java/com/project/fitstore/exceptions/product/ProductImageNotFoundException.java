package com.project.fitstore.exceptions.product;

public class ProductImageNotFoundException extends RuntimeException {
    public ProductImageNotFoundException() {
        super("Product image not found");
    }
    public ProductImageNotFoundException(String message) {
        super(message);
    }
}
