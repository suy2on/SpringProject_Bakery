package com.example.bakery.model;

import com.example.bakery.exception.InvalidPhoneNumberException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Order {
    private final UUID orderId;
    private final List<OrderItem> orderItems;
    private String phoneNumber;
    private String address;
    private long price;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(List<OrderItem> orderItems, String phoneNumber, String address, long price) throws InvalidPhoneNumberException{
        if(isValidPhoneNumber(phoneNumber)) {
            this.orderId = UUID.randomUUID();
            this.orderItems = orderItems;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.price = price;
            this.orderStatus = OrderStatus.APPROVED;
            this.createdAt = LocalDateTime.now();
        } else{
            throw new InvalidPhoneNumberException("This is invalid phone number.");
        }
    }

    public Order(UUID orderId, List<OrderItem> orderItems, String phoneNumber, String address,
        long price, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) throws InvalidPhoneNumberException {
        if(isValidPhoneNumber(phoneNumber)) {
            this.orderId = orderId;
            this.orderItems = orderItems;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.price = price;
            this.orderStatus = orderStatus;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        } else{
            throw new InvalidPhoneNumberException("This is invalid phone number.");
        }
    }

    // getter

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

    public long getPrice() {
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

    // setter

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isValidPhoneNumber(String number){
        return Pattern.matches("/^\\d{3}-\\d{3,4}-\\d{4}$/", number);
    }
}
