package com.ivandyshliuk.depotflow.inventory;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Column(nullable = false, unique = true, length = 64)
    private String code;
    @Column(nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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

    protected Warehouse() {

    }

    public Long getId() {
        return id;
    }
}
