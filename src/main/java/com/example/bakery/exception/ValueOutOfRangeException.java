package com.example.bakery.exception;

public class ValueOutOfRangeException extends RuntimeException{

    public ValueOutOfRangeException() {
    }

    public ValueOutOfRangeException(String message) {
        super(message);
    }


}
