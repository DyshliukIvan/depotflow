package com.ivandyshliuk.depotflow.inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory inventory;
    StockItem item;
    StockKey key;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        item = new StockItem(new Product("qwq", "qwqw"), new Warehouse("qwqw", "313"));
        key = new StockKey(item.getProduct().getSku(), item.getWarehouse().getCode());
    }

    @Test
    void createNewInventory () {
        assertEquals(0, inventory.size());
    }

    @Test
    void addToInventory () {
        inventory.add(item);
        assertEquals(1, inventory.size());
        assertSame(item, inventory.get(key));
        assertSame(item, inventory.get(new StockKey(item.getProduct().getSku(), item.getWarehouse().getCode())));
        assertThrows(IllegalStateException.class, () -> inventory.add(item));
        assertThrows(IllegalArgumentException.class, () -> inventory.add(null));
        assertThrows(IllegalStateException.class, () -> inventory.get(new StockKey("a", "a")));
    }
}
