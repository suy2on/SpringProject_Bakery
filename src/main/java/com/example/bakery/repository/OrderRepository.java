package com.example.bakery.repository;

import com.example.bakery.model.Order;
import com.example.bakery.model.OrderStatus;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductCategory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);


}
