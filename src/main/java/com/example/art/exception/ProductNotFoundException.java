package com.example.art.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String massage) {
        super(massage);
    }
}
