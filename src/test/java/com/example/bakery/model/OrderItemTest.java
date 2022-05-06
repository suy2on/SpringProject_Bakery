package com.example.bakery.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.bakery.exception.ValueOutOfRangeException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    @DisplayName("1개보다 적게 상품을 살 수 없다")
    void invalidOrderCountTest(){
        assertThrows(ValueOutOfRangeException.class, () ->{new OrderItem(UUID.randomUUID(), 0, 1000);});
    }


    @Test
    @DisplayName("주문 상품 가격이 0원 이하일 수 없다.")
    void invalidOrderPriceTest(){
        assertThrows(ValueOutOfRangeException.class, () ->{new OrderItem(UUID.randomUUID(), 4, 0);});
    }


}