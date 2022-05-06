package com.example.bakery.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.config.JdbcNamespaceHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class OrderJdbcRepositoryTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    OrderRepository orderJdbcRepository;

    @Autowired
    ProductRepository productJdbcRepository;

    List<OrderItem> orderItems = new ArrayList<>();

    @AfterAll
    void cleanup() {
        orderJdbcRepository.deleteAllOrder();
        productJdbcRepository.deleteAllProduct();
    }

    @Test
    @DisplayName("주문을 추가할 수 있다.")
    void insertOrderTest() {
        Product product = new Product("order-test", ProductCategory.CAKE, 2000, 5, "for order test");
        productJdbcRepository.insert(product);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(product.getProductId(), 5, 2000 * 5));
        Order order = new Order(orderItems, "010-8738-2934", "mapo", 10000);

        orderJdbcRepository.insert(order);
        List<Order> orders = orderJdbcRepository.getAllOrder();

        assertThat(orders.isEmpty(), is(false));
    }
}