package com.ivandyshliuk.depotflow.inventory;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

@Entity
@Table(name = "stock_items")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "on_hand", nullable = false)
    private int onHand;

    @Column(nullable = false)
    private int reserved;

    @Version
    private int version;

    @Transient
    private final List<StockMovement> movements = new ArrayList<>();

    public StockItem(Product newProduct, Warehouse newWarehouse) {
        if (newProduct == null ||  newWarehouse == null) throw new IllegalArgumentException();
        this.onHand = 0;
        this.reserved = 0;
        this.product = newProduct;
        this.warehouse = newWarehouse;

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

    protected StockItem() {
    }
}
