package com.example.bakery.repository;

import static com.example.bakery.Utils.toLocalDateTime;
import static com.example.bakery.Utils.toUUID;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import com.example.bakery.model.OrderStatus;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class OrderJdbcRepository implements OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderJdbcRepository.class);

    NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(
        NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // TODO : rollback문제

    @Override
    public Order insert(Order order) {
        String sql1 = "insert order(order_id, order_status, phone_number, address, price, created_at, updated_at"
            + "values(UUID_TO_BIN(:orderId), :orderStatus, :phoneNumber, :address, :price, :createdAt, :updatedAt";

        String sql2 = "insert order_item(product_id, order_id, count, price, created_at, updated_at)"
            + "values(UUID_TO_BIN(:productId), UUID_TO_BIN(:orderId), :count, :price, :createdAt, :updatedAt)";
        try {
            int insert = jdbcTemplate.update(sql1, orderParamsMap(order));
            if (insert != 1)
                logger.error("Got error during insert order");
        } catch (DataAccessException e){
            logger.error("Got error during insert order : ", e);
            throw e;
        }

        try{
            order.getOrderItems().forEach(item -> jdbcTemplate.update(sql2, orderItemParamsMap(item)));
        } catch (DataAccessException e){
            logger.error("Got error during insert orderItem :",e);
            throw e;
        }

        return order;
    }

    private Map<String, Object> orderItemParamsMap(OrderItem orderItem){
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("orderId", orderItem.getOrderId().toString().getBytes());
        objectHashMap.put("productId", orderItem.getProductId().toString().getBytes());
        objectHashMap.put("count", orderItem.getCount());
        objectHashMap.put("price", orderItem.getPrice());
        objectHashMap.put("createdAt",orderItem.getCreatedAt());
        objectHashMap.put("updatedAt",orderItem.getUpdatedAt());

        return objectHashMap;
    }


    private Map<String, Object> orderParamsMap(Order order){
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("orderId", order.getOrderId().toString().getBytes());
        objectHashMap.put("orderStatus", order.getOrderStatus().toString());
        objectHashMap.put("phoneNumber", order.getPhoneNumber());
        objectHashMap.put("price", order.getPrice());
        objectHashMap.put("address", order.getAddress());
        objectHashMap.put("createdAt", order.getCreatedAt());
        objectHashMap.put("updatedAt", order.getUpdatedAt());

        return objectHashMap;
    }
}
