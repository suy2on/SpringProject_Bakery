package com.example.bakery.service;

import com.example.bakery.dto.OrderRequestDto;
import com.example.bakery.dto.OrderResponseDto;
import com.example.bakery.model.Order;
import com.example.bakery.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto insertOrder(OrderRequestDto orderRequestDto){
        Order order = orderRepository.insert(orderRequestDto.toEntity());
        return new OrderResponseDto(order);
    }

    public List<OrderResponseDto> getAllOrder(){
        return orderRepository.getAllOrder()
            .stream()
            .map(OrderResponseDto::new)
            .collect(Collectors.toList());
    }

}
