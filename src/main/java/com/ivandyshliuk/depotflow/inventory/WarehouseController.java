package com.ivandyshliuk.depotflow.inventory;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Warehouse createWarehouse(@Valid @RequestBody CreateWarehouseRequest request) {
        return warehouseService.createWarehouse(request.code(), request.name());
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }
}
