package com.example.bakery.model;

import com.example.bakery.exception.ValueOutOfRangeException;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItem {
    private final UUID orderId;
    private final UUID productId;
    private final int count;
    private final int price;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final int MIN_PRICE = 1;
    private static final int MIN_COUNT = 1;

    public OrderItem(UUID orderId,UUID productId, int count, int price) throws ValueOutOfRangeException{
        if(isValidCount(count)){
            throw new ValueOutOfRangeException("Invalid count");
        } else if(isValidPrice(price)){
            throw new ValueOutOfRangeException("Invalid price");
        } else {
            this.orderId = orderId;
            this.productId = productId;
            this.count = count;
            this.price = price;
            this.createdAt = LocalDateTime.now();
        }
    }

    public OrderItem(UUID orderId, UUID productId, int count, int price, LocalDateTime createdAt,
        LocalDateTime updatedAt) throws ValueOutOfRangeException{
        if(isValidCount(count)){
            throw new ValueOutOfRangeException("Invalid count");
        } else if(isValidPrice(price)){
            throw new ValueOutOfRangeException("Invalid price");
        } else {
            this.orderId = orderId;
            this.productId = productId;
            this.count = count;
            this.price = price;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
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

    private boolean isValidCount(int count){
        if(count < MIN_COUNT){
            return false;
        }
        return true;
    }

    private boolean isValidPrice(int price){
        if(price < MIN_PRICE){
            return false;
        }
        return true;
    }

}
