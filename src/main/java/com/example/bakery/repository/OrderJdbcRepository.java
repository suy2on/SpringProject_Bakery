package com.example.bakery.repository;

import static com.example.bakery.Utils.toLocalDateTime;
import static com.example.bakery.Utils.toUUID;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderItem;
import com.example.bakery.model.OrderStatus;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderJdbcRepository.class);

    NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(
        NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // TODO : rollback문제

    @Override
    public List<Order> getAllOrder(){
        String sql1 = "select * from product_order";
        String sql2 = "select * from order_item where order_id = UUID_TO_BIN(:orderId)";
        List<Order> orders =jdbcTemplate.query(sql1, orderRowMapper);

        orders.forEach(order ->{
            List<OrderItem> orderItems = jdbcTemplate.query(sql2, Collections.singletonMap("orderId", order.getOrderId().toString().getBytes()), orderItemRowMapper);
            order.setOrderItems(orderItems);
        });

        return orders;
    }

    @Override
    public Order insert(Order order) {
        String sql1 = "insert product_order(order_id, order_status, phone_number, address, price, created_at, updated_at)"
            + "values(UUID_TO_BIN(:orderId), :orderStatus, :phoneNumber, :address, :price, :createdAt, :updatedAt)";

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
            order.getOrderItems().forEach(item -> jdbcTemplate.update(sql2, orderItemParamsMap(order, item)));
        } catch (DataAccessException e){
            logger.error("Got error during insert orderItem :",e);
            throw e;
        }

        return order;
    }

    @Override
    public int deleteAllOrder(){
        String sql = "delete from product_order";
        return jdbcTemplate.update(sql, Collections.emptyMap());

    }

    private RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        UUID orderId = toUUID(rs.getBytes("order_id"));
        List<OrderItem> orderItems = new ArrayList<>();
        OrderStatus orderStatus = OrderStatus.valueOf(rs.getString("order_status"));
        String phoneNumber = rs.getString("phone_number");
        String address = rs.getString("address");
        int price = rs.getInt("price");
        LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

        return new Order(orderId,orderItems,phoneNumber,address,price,orderStatus,createdAt,updatedAt);
    };

    private RowMapper<OrderItem> orderItemRowMapper = (rs, rowNum) -> {
        UUID productId = toUUID(rs.getBytes("product_id"));
        int count = rs.getInt("count");
        int price = rs.getInt("price");
        LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

        return new OrderItem(productId,count,price,createdAt,updatedAt);
    };

    private Map<String, Object> orderItemParamsMap(Order order,  OrderItem orderItem){
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("orderId", order.getOrderId().toString().getBytes());
        objectHashMap.put("productId", orderItem.getProductId().toString().getBytes());
        objectHashMap.put("count", orderItem.getCount());
        objectHashMap.put("price", orderItem.getPrice());
        objectHashMap.put("createdAt",order.getCreatedAt());
        objectHashMap.put("updatedAt",order.getUpdatedAt());

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
