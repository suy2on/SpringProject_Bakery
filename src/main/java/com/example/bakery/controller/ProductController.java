package com.example.bakery.controller;


import com.example.bakery.dto.ProductRequestDto;
import com.example.bakery.dto.ProductResponseDto;
import com.example.bakery.dto.ProductUpdateDescriptionDto;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import com.example.bakery.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품전체조회
    // 특정 카테고리 상품 조회
    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProduct(@RequestParam(required = false)  String productCategory) {
        if (productCategory != null) {
            List<ProductResponseDto> productByCategory = productService.findProductByCategory(productCategory);
            return ResponseEntity.status(HttpStatus.OK).body(productByCategory);
        } else {
            List<ProductResponseDto> products = productService.getAllProduct();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
    }

    // 특정 상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("productId") UUID productId){
        ProductResponseDto product = productService.findProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    // 상품 추가
    @PostMapping("")
    public ResponseEntity<ProductResponseDto> registerProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto result = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateDescriptionOfProduct(@PathVariable("productId") UUID productId, @RequestBody
        ProductUpdateDescriptionDto productUpdateDescriptionDto) {
        ProductResponseDto productResponseDto = productService.updateDescriptionOfProduct(productId, productUpdateDescriptionDto.description());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }


    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<UUID> deleteProduct(@PathVariable("productId") UUID productId){
        UUID result = productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }








}
