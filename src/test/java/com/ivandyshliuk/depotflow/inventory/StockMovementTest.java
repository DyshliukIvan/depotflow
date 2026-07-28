package com.ivandyshliuk.depotflow.inventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
public class StockMovementTest {
    Instant time;
    @BeforeEach
    void setUp() {
        time = Instant.parse("2026-07-26T10:00:00Z");
    }

    @Test
    void createsStockMovementWithTypeAndQuantityAndTime(){
        StockMovement stockMovement = new StockMovement(MovementType.SHIP, 5, time);
        assertEquals(MovementType.SHIP, stockMovement.getType());
        assertEquals(5,stockMovement.getQuantity());
        assertEquals(time, stockMovement.getOccurredAt());
    }

    @Test
    void createsStockMovementWithNull(){
        assertThrows(IllegalArgumentException.class, () -> new StockMovement(null, 5, time));
        assertThrows(IllegalArgumentException.class, () -> new StockMovement(MovementType.SHIP, 5, null));

    }

    @Test
    void createsStockMovementWithWrongQuantity(){
        assertThrows(IllegalArgumentException.class, () -> new StockMovement(MovementType.SHIP, 0, time));
        assertThrows(IllegalArgumentException.class, () -> new StockMovement(MovementType.SHIP, -1, time));
    }
}
