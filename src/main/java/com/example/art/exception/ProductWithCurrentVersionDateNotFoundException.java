package com.example.art.exception;

public class ProductWithCurrentVersionDateNotFoundException extends RuntimeException {
    public ProductWithCurrentVersionDateNotFoundException(String message) {
        super(message);
    }
}
