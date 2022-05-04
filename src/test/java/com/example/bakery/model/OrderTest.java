package com.example.bakery.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.bakery.exception.InvalidPhoneNumberException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;

class OrderTest {

    @Test
    @DisplayName("010-34523-3444는 유효하지 않은 전화번호입니다.")
    void inValidPhoneNumberTest() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(UUID.randomUUID(), UUID.randomUUID(), 5, 5000));
        assertThrows(InvalidPhoneNumberException.class, () -> {new Order(orderItems, "010-34523-3444", "address", 5000L);});

    }


//    @Test
//    @DisplayName("010-3453-3444는 유효한 전화번호입니다.")
//    void ValidPhoneNumberTest() {
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(UUID.randomUUID(), UUID.randomUUID(), 5, 5000));
//        assertThrows(InvalidPhoneNumberException.class, () -> {new Order(orderItems, "010-3453-3444", "address", 5000L);});
//
//    }


}