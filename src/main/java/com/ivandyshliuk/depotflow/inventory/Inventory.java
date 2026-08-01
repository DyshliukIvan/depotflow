package com.ivandyshliuk.depotflow.inventory;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<StockKey, StockItem> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public int size() {
        return items.size();
    }

    public StockItem get(StockKey key) {
        if (key == null) throw new IllegalArgumentException();
        if (!items.containsKey(key)) throw new IllegalStateException();
        return items.get(key);
    }

    public void add(StockItem item) {
        if (item == null) throw new IllegalArgumentException();
        if (items.containsKey(new StockKey(item.getProduct().getSku(), item.getWarehouse().getCode()))) throw new IllegalStateException();
        items.put(new StockKey(item.getProduct().getSku(), item.getWarehouse().getCode()), item);
    }
}
