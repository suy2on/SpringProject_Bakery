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
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProduct() {
        return productRepository.getAllProduct()
            .stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }

    public ProductResponseDto findProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return new ProductResponseDto(product);

    }

    public List<ProductResponseDto> findProductByCategory(ProductCategory productCategory){
        return productRepository.findByCategory(productCategory)
            .stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }


    // TODO : Update Date 바뀌도록 로직 수정
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto){
        Product product = productRepository.update(productRequestDto.toEntity());
        return new ProductResponseDto(product);
    }

    public UUID deleteProductById(UUID productId){
        return productRepository.deleteById(productId);
    }
}
