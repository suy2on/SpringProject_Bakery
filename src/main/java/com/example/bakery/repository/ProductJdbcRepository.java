package com.example.bakery.repository;

import static com.example.bakery.Utils.toLocalDateTime;
import static com.example.bakery.Utils.toUUID;

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
import org.springframework.stereotype.Repository;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductJdbcRepository.class);

    NamedParameterJdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(
        NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        String sql = "select * from product where product_id = UUID_TO_BIN(:productId)";
        try {
            return Optional.ofNullable(jdbcTemplate
                .queryForObject(sql, Collections.singletonMap("productId", productId.toString().getBytes()),
                    productRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.error("There is no data matching the ID : ", e);
            throw e;
        }catch (IncorrectResultSizeDataAccessException e){
            logger.error("An error occurred while finding product by Id : ", e);
            throw e;
        }
    }

    @Override
    public List<Product> findByCategory(ProductCategory productCategory) {
        String sql = "select * from product where product_category = :category";
        return jdbcTemplate.query(sql, Collections.singletonMap("category", productCategory.toString()), productRowMapper);
    }

    @Override
    public Product insert(Product product) {
        String sql = "insert into product(product_id, name, product_category, price, stock, description, created_at, updated_at) "
            + "values(UUID_TO_BIN(:productId), :productName, :category, :price, :stock, :description, :createdAt, :updatedAt)";

        try {
            int insert = jdbcTemplate.update(sql, productParamsMap(product));
            if (insert != 1)
                logger.error("Got error during insert product");
        } catch (DataAccessException e){
            logger.error("Got error during insert product : ", e);
            throw e;
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        String sql = "update product set name = :productName, product_category = :category, price = :price, stock = :stock, description = :description, "
            + "created_at = :createdAt, updated_at = :updatedAt where product_id = UUID_TO_BIN(:productId)" ;
        try {
            int update = jdbcTemplate.update(sql, productParamsMap(product));
            if (update != 1)
                logger.error("Got error during update product");
        } catch (DataAccessException e){
            logger.error("Got error during update product : " ,e);
            throw e;
        }
        return product;

    }

    @Override
    public UUID deleteById(UUID productId) {
        String sql = "delete from product where product_id = UUID_TO_BIN(:productId)";
        jdbcTemplate.update(sql, Collections.singletonMap("productId", productId.toString().getBytes()));
        return productId;
    }

    @Override
    public int deleteAllProduct(){
        String sql = "delete from product";
        int delete = jdbcTemplate.update(sql, Collections.emptyMap());
        return delete;
    }


    private static final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        UUID productId = toUUID(rs.getBytes("product_id"));
        String productName = rs.getString("name");
        ProductCategory productCategory = ProductCategory.valueOf(rs.getString("product_category"));
        int price = rs.getInt("price");
        int stock = rs.getInt("stock");
        String description = rs.getString("description");
        LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

        return new Product(productId, productName, productCategory, price, stock, description, createdAt, updatedAt);
    };

    private Map<String, Object> productParamsMap(Product product){
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("productId", product.getProductId().toString().getBytes());
        objectHashMap.put("productName", product.getProductName());
        objectHashMap.put("category", product.getProductCategory().toString());
        objectHashMap.put("price", product.getPrice());
        objectHashMap.put("stock", product.getStock());
        objectHashMap.put("description", product.getDescription());
        objectHashMap.put("createdAt", product.getCreatedAt());
        objectHashMap.put("updatedAt", product.getUpdatedAt());

        return objectHashMap;
    }
}
