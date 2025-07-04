package com.project.fitstore.exceptions.product;

public class DuplicateProductSkuException extends RuntimeException {
    public DuplicateProductSkuException() {
        super("Sku already in use");
    }

    public DuplicateProductSkuException(String message) {
        super(message);
    }
}
