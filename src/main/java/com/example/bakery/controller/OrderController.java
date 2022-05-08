package com.example.bakery.controller;

import com.example.bakery.dto.OrderRequestDto;
import com.example.bakery.dto.OrderResponseDto;
import com.example.bakery.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 전체 주문 가져오기
    @GetMapping("")
    public ResponseEntity<List<OrderResponseDto>> getAllOrder(){
        List<OrderResponseDto> orders = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // 주문 추가하기
    @PostMapping("")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto order = orderService.insertOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }
}
