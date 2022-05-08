package com.example.bakery.dto;

import com.example.bakery.model.OrderItem;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderItemRequestDto(UUID productId, int count, int price) {

        public OrderItem toEntity() {
            return new OrderItem(productId,count, price);
        }
}
