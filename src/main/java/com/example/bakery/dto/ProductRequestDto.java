package com.example.bakery.dto;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;

public record ProductRequestDto(String productName, String productCategory, int price, int stock, String description){
        public Product toEntity(){
            return new Product(productName, ProductCategory.valueOf(productCategory), price,stock, description);
        }


}
