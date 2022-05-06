package com.example.bakery.dto;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductResponseDto{
    private UUID productId;
    private String productName;
    private ProductCategory productCategory;
    private int price;
    private int stock;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductResponseDto(Product product) {
            this.productId = product.getProductId();
            this.productName = product.getProductName();
            this.productCategory = product.getProductCategory();
            this.price = product.getPrice();
            this.stock = product.getStock();
            this.description = product.getDescription();
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
