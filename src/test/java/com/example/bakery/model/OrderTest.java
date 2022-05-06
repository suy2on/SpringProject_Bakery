package com.example.bakery.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.example.bakery.exception.InvalidPhoneNumberException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;

class OrderTest {

    @Test
    @DisplayName("010-34583-3444는 유효하지 않은 전화번호입니다.")
    void inValidPhoneNumberTest() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(UUID.randomUUID(), 5, 5000));
        assertThrows(InvalidPhoneNumberException.class, () -> {new Order(orderItems, "010-34583-3444", "address", 5000L);});

    }

    @Test
    void validPhoneNumberTest(){
        assertThat(Pattern.matches("\\d{3}-\\d{4}-\\d{4}", "010-3444-2333"), is(true));
    }



}