package com.ivandyshliuk.depotflow.inventory;

public class Warehouse {
    private final String code;
    private final String name;

    public Warehouse(String warehouseCode, String warehouseName) {
        if (warehouseCode == null || warehouseName == null || warehouseCode.isBlank() || warehouseName.isBlank()) throw new IllegalArgumentException();
        this.code = warehouseCode;
        this.name = warehouseName;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
