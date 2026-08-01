package com.ivandyshliuk.depotflow.inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class StockKeyTest {
    StockKey stockkey;

    @BeforeEach
    void setUp() {
        stockkey = new StockKey("abc", "adc");
    }

    @Test
    void createStockKeyWithSkuAndWarehouse() {
        assertEquals("abc", stockkey.productSku());
        assertEquals("adc", stockkey.warehouseCode());
    }

    @Test
    void create2IdenticalStockKey() {
        StockKey stockkey1 = new StockKey("abc", "adc");
        assertTrue(stockkey1.equals(stockkey));
        assertTrue(stockkey1.hashCode() == stockkey.hashCode());
    }

    @Test
    void create2DifferentStockKey() {
        StockKey stockkey1 = new StockKey("arc", "bdc");
        assertFalse(stockkey1.equals(stockkey));
        assertFalse(stockkey1.productSku().equals(stockkey.productSku()));
        assertFalse(stockkey1.warehouseCode().equals(stockkey.warehouseCode()));
    }


    @Test
    void createStockKeyWithException() {
        assertThrows(IllegalArgumentException.class, () -> new StockKey("", ""));
        assertThrows(IllegalArgumentException.class, () -> new StockKey(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> new StockKey("null", null));
        assertThrows(IllegalArgumentException.class, () -> new StockKey("            ", "adasd"));
    }

    @Test
    void createHashMapWithStockKeyAndString() {
        StockKey stockkey1 = new StockKey("abc", "adc");
        HashMap<StockKey, String> hashmap = new HashMap<>();
        hashmap.put(stockkey, "qwe");
        assertEquals("qwe", hashmap.get(stockkey1));
    }

}
