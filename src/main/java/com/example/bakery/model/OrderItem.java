package com.example.bakery.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItem {
    private final UUID orderId;
    private final UUID productId;
    private final int count;
    private final int price;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem(UUID orderId,UUID productId, int count, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    public OrderItem(UUID orderId, UUID productId, int count, int price, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getter

    public UUID getOrderId() {
        return orderId;
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
