package com.example.bakery.dto;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import com.example.bakery.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponseDto {
    private UUID orderId;
    private List<OrderItem>orderItems;
    private String phoneNumber;
    private String address;
    private Long price;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderResponseDto(Order order) {
            this.orderId = order.getOrderId();
            this.orderItems = order.getOrderItems();
            this.phoneNumber = order.getPhoneNumber();
            this.address = order.getAddress();
            this.price = order.getPrice();
            this.orderStatus = order.getOrderStatus();
            this.createdAt = order.getCreatedAt();
            this.updatedAt = order.getUpdatedAt();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Long getPrice() {
        return price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
