package com.example.bakery.dto;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDto(UUID productId, String productName, ProductCategory productCategory, int price
, int stock, String description, LocalDateTime createdAt, LocalDateTime updatedAt){

}
