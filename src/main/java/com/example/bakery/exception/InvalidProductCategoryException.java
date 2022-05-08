package com.example.bakery.exception;

public class InvalidProductCategoryException extends RuntimeException {

    public InvalidProductCategoryException() {
    }

    public InvalidProductCategoryException(String message) {
        super(message);
    }
}
