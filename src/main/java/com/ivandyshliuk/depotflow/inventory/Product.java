package com.ivandyshliuk.depotflow.inventory;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Column(nullable = false, unique = true, length = 64)
    private String sku;
    @Column(nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    protected Product() {
    }

    public Long getId() {
        return id;
    }
}
