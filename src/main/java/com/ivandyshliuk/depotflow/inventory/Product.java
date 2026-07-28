package com.ivandyshliuk.depotflow.inventory;

public class Product {
    private final String sku;
    private final String name;

    public Product(String productSku, String productName) {
        if (productSku == null || productName == null || productSku.isBlank() || productName.isBlank()) throw new IllegalArgumentException();
        this.sku = productSku;
        this.name = productName;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }
}
