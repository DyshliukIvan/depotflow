package com.ivandyshliuk.depotflow.inventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

class StockItemTest {

    private StockItem item;

    @BeforeEach
    void setUp(){
        item = new StockItem(new Product("iphone-17-pro", "Iphone 17 pro"), new Warehouse("BA-01", "Bratislava Central Warehouse"));
    }

    @Test
    void newStockItemStartsEmpty() {
        assertEquals(0, item.getOnHand());
        assertEquals(0, item.getReserved());
        assertEquals(0, item.getAvailable());
    }

    @Test
    void receivingStockIncreasesOnHandAndAvailable() {
        item.receive(5);
        assertEquals(5, item.getOnHand());
        assertEquals(0, item.getReserved());
        assertEquals(5, item.getAvailable());
    }

    @Test
    void reservingStockReducesAvailableQuantity() {
        item.receive(5);
        item.reserve(4);
        assertEquals(4, item.getReserved());
        assertEquals(1, item.getAvailable());
        assertEquals(5, item.getOnHand());
    }

    @Test
    void cannotReceiveNonPositiveQuantity() {
        assertThrows(IllegalArgumentException.class, () -> item.receive(-20));
    }

    @Test
    void cannotReserveNonPositiveQuantity() {
        item.receive(5);
        assertThrows(IllegalArgumentException.class, () -> item.reserve(-20));
    }

    @Test
    void cannotReserveMoreThanAvailable() {
        item.receive(5);
        assertThrows(IllegalStateException.class, () -> item.reserve(6));
    }

    @Test
    void newStockItemProductWarehouse() {
        Product product = new Product("iphone-17-pro", "Iphone 17 pro");
        Warehouse warehouse = new Warehouse("BA-01", "Bratislava Central Warehouse");
        StockItem item = new StockItem(product, warehouse);
        assertSame(product, item.getProduct());
        assertSame(warehouse, item.getWarehouse());
    }

    @Test
    void newStockItemProductWarehouseNull() {
        assertThrows(IllegalArgumentException.class, () -> new StockItem( new Product("iphone-17-pro", "Iphone 17 pro"), null));
        assertThrows(IllegalArgumentException.class, () -> new StockItem(null, new Warehouse("BA-01", "Bratislava Central Warehouse")));
    }

    @Test
    void releasingReservationMakesStockAvailableAgain() {
        item.receive(5);
        item.reserve(4);
        item.release(2);
        assertEquals(2, item.getReserved());
        assertEquals(3, item.getAvailable());
        assertEquals(5, item.getOnHand());
    }

    @Test
    void cannotReleaseNonPositiveQuantity() {
        item.receive(5);
        item.reserve(4);
        assertThrows(IllegalArgumentException.class, () -> item.release(-1));
    }

    @Test
    void cannotReleaseMoreThanReserved() {
        item.receive(5);
        item.reserve(4);
        assertThrows(IllegalStateException.class, () -> item.release(5));
    }

    @Test
    void shippingReservedStockReducesOnHandAndReserved() {
        item.receive(5);
        item.reserve(4);
        item.ship(2);
        assertEquals(2, item.getReserved());
        assertEquals(1, item.getAvailable());
        assertEquals(3, item.getOnHand());
    }

    @Test
    void cannotShipNonPositiveQuantity() {
        item.receive(5);
        item.reserve(4);
        assertThrows(IllegalArgumentException.class, () -> item.ship(-1));
    }

    @Test
    void cannotShipMoreThanReserved() {
        item.receive(5);
        item.reserve(4);
        assertThrows(IllegalStateException.class, () -> item.ship(5));
    }

    @Test
    void createsStockItemWithEmptyHistory(){
        assertEquals(new ArrayList<>(), item.getMovements());
    }

    @Test
    void createsStockItemWithReceive(){
        item.receive(5);
        assertEquals(MovementType.RECEIVE, item.getMovements().get(0).getType());
        assertEquals(1, item.getMovements().size());
        assertEquals(5, item.getMovements().get(0).getQuantity());
    }

    @Test
    void createsStockItemWithAllTypes(){
        item.receive(5);
        item.reserve(2);
        item.release(1);
        item.ship(1);
        assertEquals(MovementType.RECEIVE, item.getMovements().get(0).getType());
        assertEquals(MovementType.RESERVE, item.getMovements().get(1).getType());
        assertEquals(MovementType.RELEASE, item.getMovements().get(2).getType());
        assertEquals(MovementType.SHIP, item.getMovements().get(3).getType());
    }

    @Test
    void createsStockItemWithException(){
        assertThrows(IllegalArgumentException.class, () -> item.receive(-1));
        assertEquals(new ArrayList<>(), item.getMovements());
    }

    @Test
    void createsStockItemTryToMakeException(){
        assertThrows(UnsupportedOperationException.class, () -> item.getMovements().clear());
    }

}
