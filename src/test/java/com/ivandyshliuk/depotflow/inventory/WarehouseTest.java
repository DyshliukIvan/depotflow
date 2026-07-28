package com.ivandyshliuk.depotflow.inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class WarehouseTest {

    Warehouse warehouse;

    void setUp() {
        warehouse =  new Warehouse("BA-01", "Bratislava Central Warehouse");
    }

    @Test
    void createsWarehouseWithCodeAndName(){
        warehouse =  new Warehouse("BA-01", "Bratislava Central Warehouse");
        assertEquals("BA-01", warehouse.getCode());
        assertEquals("Bratislava Central Warehouse", warehouse.getName());
    }

    @Test
    void cannotCreateWarehouseWithNullCode(){
        assertThrows(IllegalArgumentException.class, () -> new Warehouse(null, "Bratislava Central Warehouse"));
    }

    @Test
    void cannotCreateWarehouseWithBlankCode(){
        assertThrows(IllegalArgumentException.class, () -> new Warehouse("", "Bratislava Central Warehouse"));
    }

    @Test
    void cannotCreateWarehouseWithNullName(){
        assertThrows(IllegalArgumentException.class, () -> new Warehouse("BA-01", null));
    }

    @Test
    void cannotCreateWarehouseWithBlankName(){
        assertThrows(IllegalArgumentException.class, () -> new Warehouse("BA-01", ""));
    }
}
