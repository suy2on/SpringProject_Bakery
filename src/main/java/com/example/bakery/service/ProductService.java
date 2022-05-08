package com.example.bakery.service;

import com.example.bakery.dto.ProductRequestDto;
import com.example.bakery.dto.ProductResponseDto;
import com.example.bakery.exception.InvalidProductCategoryException;
import com.example.bakery.exception.ProductNotFoundException;
import com.example.bakery.exception.ValueOutOfRangeException;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import com.example.bakery.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        if (isValidCategory(productRequestDto.productCategory())) {
            Product product = productRepository.insert(productRequestDto.toEntity());
            return new ProductResponseDto(product);
        } else {
            throw new InvalidProductCategoryException("Product Category must be CAKE, COOKIE or MUFFIN");
        }
    }

    public List<ProductResponseDto> getAllProduct() {
        return productRepository.getAllProduct()
            .stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }

    public ProductResponseDto findProductById(UUID productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return new ProductResponseDto(product);

    }

    public List<ProductResponseDto> findProductByCategory(String productCategory) throws InvalidProductCategoryException{
        if (isValidCategory(productCategory)) {
            return productRepository.findByCategory(ProductCategory.valueOf(productCategory))
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
        } else {
            throw new InvalidProductCategoryException("Product Category must be CAKE, COOKIE or MUFFIN");
        }
    }


    public ProductResponseDto updateDescriptionOfProduct(UUID productId, String description){
        Optional<Product> product = productRepository.findById(productId);
        product.orElseThrow(ProductNotFoundException::new).setDescription(description);
        Product updateProduct = productRepository.update(product.get());
        return new ProductResponseDto(updateProduct);
    }

    public void updateStockOfProduct(UUID productId, int stock) throws ValueOutOfRangeException {
        Optional<Product> product = productRepository.findById(productId);
        product.orElseThrow(ProductNotFoundException::new).setStock(stock);
        productRepository.update(product.get());
    }

    public UUID deleteProductById(UUID productId){
        return productRepository.deleteById(productId);
    }

    private boolean isValidCategory(String productCategory){
        for(ProductCategory category : ProductCategory.values()){
            if(productCategory.equals(category.name())) {
                return true;
            }
        }
        return false;
    }
}
