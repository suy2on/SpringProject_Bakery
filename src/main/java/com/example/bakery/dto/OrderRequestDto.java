package com.example.bakery.dto;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import java.util.List;

public record OrderRequestDto(List<OrderItem> orderItems, String phoneNumber, String address, long price) {

    public Order toEntity(){
        return new Order(orderItems(), phoneNumber(), address(), price());
    }
}
