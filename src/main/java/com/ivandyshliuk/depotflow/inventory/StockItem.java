package com.ivandyshliuk.depotflow.inventory;

public class StockItem {

    private int onHand;
    private int reserved;
    private final Product product;
    private final Warehouse warehouse;

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
    }

    public void reserve(int quantity) {
        var available = getAvailable();
        if (quantity <= 0) throw new IllegalArgumentException();
        if (quantity > available) throw new IllegalStateException();
        reserved += quantity;
    }

    public void release(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        if (reserved < quantity) throw new IllegalStateException();
        reserved -= quantity;
    }

    public void ship(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        if (reserved < quantity || onHand < quantity) throw new IllegalStateException();
        reserved -= quantity;
        onHand -= quantity;
    }
}
