package com.example.bakery.controller;


import com.example.bakery.exception.InvalidPhoneNumberException;
import com.example.bakery.exception.InvalidProductCategoryException;
import com.example.bakery.exception.ProductNotFoundException;
import com.example.bakery.exception.ValueOutOfRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class bakeryControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handlerNotFoundException (ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerInvalidPhoneNumber (InvalidPhoneNumberException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerInvalidProductCategory(InvalidProductCategoryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerValueOutOfRange(ValueOutOfRangeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
