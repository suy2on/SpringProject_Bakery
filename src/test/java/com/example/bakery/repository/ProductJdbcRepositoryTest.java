package com.example.bakery.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    ProductRepository productJdbcRepository;

    Product product = new Product("product", ProductCategory.COOKIE, 5000, 20, "유기농 쿠키입니다");

    @AfterAll
    void cleanup() {
        productJdbcRepository.deleteAllProduct();
    }


    @Test
    @Order(1)
    @DisplayName("새로운 상품을 추가하기")
    void insertTest() {
        productJdbcRepository.insert(product);
        List<Product> products = productJdbcRepository.getAllProduct();

        assertThat(products.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    @DisplayName("id로 상품을 찾을 수 있다")
    void findById() {
        Optional<Product> findProduct = productJdbcRepository.findById(product.getProductId());

        assertThat(findProduct.isEmpty(), is(false));
        assertThat(findProduct.get(), samePropertyValuesAs(product));
    }

    @Test
    @Order(3)
    @DisplayName("카테고리별로 상품을 찾을 수 있다")
    void findByCategory() {
        List<Product> findProduct = productJdbcRepository.findByCategory(ProductCategory.COOKIE);

        findProduct.forEach(product1 ->
        {assertThat(product1.getProductCategory(),is(ProductCategory.COOKIE));});

    }


    @Test
    @Order(4)
    @DisplayName("상품을 수정할 수 있다.")
    void update() {
        product.setStock(product.getStock()-1);
        Product updateProduct = productJdbcRepository.update(product);
        Optional<Product> find_product = productJdbcRepository.findById(product.getProductId());

        assertThat(find_product.isEmpty(), is(false));
        assertThat(find_product.get(), samePropertyValuesAs(updateProduct));
    }

    @Test
    @Order(5)
    @DisplayName("id로 상품을 삭제할 수 있다.")
    void deleteById() {
        productJdbcRepository.deleteById(product.getProductId());
        List<Product> products = productJdbcRepository.getAllProduct();

        assertThat(products.isEmpty(),is(true));
    }
}