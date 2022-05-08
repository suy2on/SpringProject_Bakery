package com.example.bakery.service;

import com.example.bakery.dto.OrderRequestDto;
import com.example.bakery.dto.OrderResponseDto;
import com.example.bakery.dto.ProductResponseDto;
import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import com.example.bakery.model.Product;
import com.example.bakery.repository.OrderRepository;
import com.example.bakery.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    OrderRepository orderRepository;
    ProductRepository productRepository;
    ProductService productService;

    public OrderService(OrderRepository orderRepository,
        ProductRepository productRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public OrderResponseDto insertOrder(OrderRequestDto orderRequestDto){
        // 재고줄이기
        Order order = orderRequestDto.toEntity();
        for(OrderItem orderItem : order.getOrderItems()){
            Optional<Product> product = productRepository.findById(orderItem.getProductId());
            productService.updateStockOfProduct(product.get().getProductId(), product.get().getStock() - orderItem.getCount());
        }

        Order insert_order = orderRepository.insert(order);
        return new OrderResponseDto(insert_order);
    }

    public List<OrderResponseDto> getAllOrder(){
        return orderRepository.getAllOrder()
            .stream()
            .map(OrderResponseDto::new)
            .collect(Collectors.toList());
    }

}
