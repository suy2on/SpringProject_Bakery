package com.example.bakery.service;

import com.example.bakery.dto.ProductRequestDto;
import com.example.bakery.dto.ProductResponseDto;
import com.example.bakery.exception.ProductNotFoundException;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import com.example.bakery.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productRepository.insert(productRequestDto.toEntity());
        ProductResponseDto productResponseDto = new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductCategory(),
                product.getPrice(),
                product.getStock(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getUpdatedAt());

        return productResponseDto;
    }

    public List<ProductResponseDto> getAllProduct() {
        return productRepository.getAllProduct()
            .stream()
            .map(product -> {
                return new ProductResponseDto(
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductCategory(),
                    product.getPrice(),
                    product.getStock(),
                    product.getDescription(),
                    product.getCreatedAt(),
                    product.getUpdatedAt());
            })
            .collect(Collectors.toList());
    }

    public ProductResponseDto findProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return new ProductResponseDto(
            product.getProductId(),
            product.getProductName(),
            product.getProductCategory(),
            product.getPrice(),
            product.getStock(),
            product.getDescription(),
            product.getCreatedAt(),
            product.getUpdatedAt());
    }

    public List<ProductResponseDto> findProductByCategory(ProductCategory productCategory){
        return productRepository.findByCategory(productCategory)
            .stream()
            .map(product -> {
                return new ProductResponseDto(
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductCategory(),
                    product.getPrice(),
                    product.getStock(),
                    product.getDescription(),
                    product.getCreatedAt(),
                    product.getUpdatedAt());
            })
            .collect(Collectors.toList());
    }

    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto){
        Product product = productRepository.update(productRequestDto.toEntity());
        return new ProductResponseDto(
            product.getProductId(),
            product.getProductName(),
            product.getProductCategory(),
            product.getPrice(),
            product.getStock(),
            product.getDescription(),
            product.getCreatedAt(),
            product.getUpdatedAt());
    }

    public UUID deleteProductById(UUID productId){
        return productRepository.deleteById(productId);
    }
}
