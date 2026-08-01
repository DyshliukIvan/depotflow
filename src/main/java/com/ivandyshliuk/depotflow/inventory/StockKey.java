package com.ivandyshliuk.depotflow.inventory;

public record StockKey(String productSku, String warehouseCode) {
    public StockKey {
        if (productSku == null || warehouseCode == null || productSku.isBlank() || warehouseCode.isBlank())  throw new IllegalArgumentException();

    }

}
