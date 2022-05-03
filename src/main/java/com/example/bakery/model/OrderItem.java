package com.example.bakery.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItem {
    private final UUID orderItemId;
    private final UUID productId;
    private final int count;
    private final int price;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem(UUID productId, int count, int price) {
        this.orderItemId = UUID.randomUUID();
        this.productId = productId;
        this.count = count;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    public OrderItem(UUID orderItemId, UUID productId, int count, int price, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.count = count;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getter

    public UUID getOrderItemId() {
        return orderItemId;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }



}
