package com.example.bakery.repository;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> getAllProduct();

    Optional<Product> findById(UUID productId);

    List<Product> findByCategory(ProductCategory productCategory);

    Product insert(Product product);

    Product update(Product product);

    UUID deleteById(UUID productId);


}
