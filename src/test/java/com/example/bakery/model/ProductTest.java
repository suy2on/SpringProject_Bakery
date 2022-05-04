package com.example.bakery.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.bakery.exception.ValueOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("상품의 가격은 음수일 수 없습니다")
    void minusPriceTest() {
        assertThrows(ValueOutOfRangeException.class,
            () -> { new Product("test", ProductCategory.CAKE, -100, 5, "test용");});

    }

    @Test
    @DisplayName("상품의 가격은 100만원을 넘을 수 없습니다")
    void largePriceTest() {
        assertThrows(ValueOutOfRangeException.class,
            () -> { new Product("test", ProductCategory.CAKE, 1_000_001, 5, "test용");});

    }

    @Test
    @DisplayName("재고는 음수일 수 없습니다")
    void minusStockTest() {
        assertThrows(ValueOutOfRangeException.class,
            () -> { new Product("test", ProductCategory.CAKE, 1000, -5, "test용");});
    }

    @Test
    @DisplayName("재고는 500을 넘을 수 없습니다")
    void largeStockTest() {
        assertThrows(ValueOutOfRangeException.class,
            () -> { new Product("test", ProductCategory.CAKE, 1000, 501, "test용");});
    }
}