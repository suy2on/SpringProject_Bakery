package com.example.bakery.exception;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException() {
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }


}
