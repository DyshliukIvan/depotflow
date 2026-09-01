package com.ivandyshliuk.depotflow.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse createWarehouse(String code, String name) {
        if (warehouseRepository.existsByCode(code)) {
            throw new DublicateResourseExeption("Waregouse with code " + code + " already exists");
        }

        Warehouse warehouse = new Warehouse(code, name);
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}
