package com.ivandyshliuk.depotflow.inventory;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

public class StockItem {

    private int onHand;
    private int reserved;
    private final Product product;
    private final Warehouse warehouse;
    private final List<StockMovement> movements;

    public StockItem(Product newProduct, Warehouse newWarehouse) {
        if (newProduct == null ||  newWarehouse == null) throw new IllegalArgumentException();
        this.onHand = 0;
        this.reserved = 0;
        this.product = newProduct;
        this.warehouse = newWarehouse;
        this.movements = new ArrayList<>();

    }

    public int getOnHand() {
        return onHand;
    }

    public int getReserved() {
        return reserved;
    }

    public int getAvailable() {
        return onHand - reserved;
    }

    public Warehouse getWarehouse() { return warehouse; }

    public Product getProduct() { return product; }

    public void receive(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        onHand += quantity;
        movements.add(new StockMovement(MovementType.RECEIVE, quantity, Instant.now()));
    }

    public void reserve(int quantity) {
        var available = getAvailable();
        if (quantity <= 0) throw new IllegalArgumentException();
        if (quantity > available) throw new IllegalStateException();
        reserved += quantity;
        movements.add(new StockMovement(MovementType.RESERVE, quantity, Instant.now()));
    }

    public void release(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        if (reserved < quantity) throw new IllegalStateException();
        reserved -= quantity;
        movements.add(new StockMovement(MovementType.RELEASE, quantity, Instant.now()));
    }

    public void ship(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        if (reserved < quantity || onHand < quantity) throw new IllegalStateException();
        reserved -= quantity;
        onHand -= quantity;
        movements.add(new StockMovement(MovementType.SHIP, quantity, Instant.now()));
    }

    public List<StockMovement> getMovements() {
        return List.copyOf(movements);
    }
}
