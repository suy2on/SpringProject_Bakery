package com.example.bakery.dto;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import java.util.List;
import java.util.stream.Collectors;

public record OrderRequestDto(List<OrderItemRequestDto> orderItems, String phoneNumber, String address, long price) {

    public Order toEntity(){
        List<OrderItem> orderItems = orderItems()
            .stream()
            .map(OrderItemRequestDto::toEntity)
            .collect(Collectors.toList());

        return new Order(orderItems, phoneNumber(), address(), price());
    }
}
