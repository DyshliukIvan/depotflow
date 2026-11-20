package com.ivandyshliuk.depotflow.inventory;


import org.springframework.stereotype.Service;

@Service
public class StockItemService {

    private final StockItemRepository stockItemRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockItemService(StockItemRepository stockItemRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.stockItemRepository = stockItemRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public StockItem createStockItem(Long productId, Long warehouseId) {
        if (stockItemRepository.existsByProduct_IdAndWarehouse_Id(productId, warehouseId)) {
            throw new DublicateResourseExeption("Sto" +
                    "Item with Product id " + productId + " already exists");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found: " + warehouseId));
        StockItem stockItem = new StockItem(product, warehouse);
        return stockItemRepository.save(stockItem);
    }
}