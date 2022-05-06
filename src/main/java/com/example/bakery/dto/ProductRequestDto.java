package com.example.bakery.dto;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;

public record ProductRequestDto(String productName, ProductCategory productCategory, int price, int stock, String description){
        public Product toEntity(){
            return new Product(productName, productCategory, price,stock, description);
        }
}
