package com.example.bakery.model;

import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Product {

    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    private final UUID productId;
    private ProductCategory productCategory;
    private int price;
    private int stock;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final int MAX_PRICE = 1_000_000;
    private static final int MIN_PRICE = 0;
    private static final int MAX_STOCK = 500;
    private static final int MIN_STOCK = 0;

    public Product(ProductCategory productCategory, Integer price, Integer stock) {
        if(isValidPrice(price) && isValidStock(stock)) {
            this.productId = UUID.randomUUID();
            this.productCategory = productCategory;
            this.price = price;
            this.stock = stock;
            this.createdAt = LocalDateTime.now();
        } else{
            throw new RuntimeException("Invalid stock or price");
        }
    }

    public Product(UUID productId, ProductCategory productCategory, Integer price,
        Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if(isValidPrice(price) && isValidStock(stock)) {
            this.productId = productId;
            this.productCategory = productCategory;
            this.price = price;
            this.stock = stock;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        } else{
            throw new RuntimeException("Invalid stock or price");
        }
    }

    // getter

    public UUID getProductId() {
        return productId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isValidPrice(int price){
        if(price>MAX_PRICE){
            logger.error("Product price cannot exceed 1,000,000won.");
            return false;
        } else if(price<MIN_PRICE){
            logger.error("Product price should not be less than 0won.");
            return false;
        } else{
            return true;
        }
    }

    public boolean isValidStock(int stock) {
        if (stock > MAX_STOCK) {
            logger.error("Product stock cannot exceed 500.");
            return false;
        } else if (stock < MIN_STOCK) {
            logger.error("Product stock should not be less than 0.");
            return false;
        } else {
            return true;
        }

    }

}