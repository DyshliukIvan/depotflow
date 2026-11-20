package com.ivandyshliuk.depotflow.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    boolean existsByProduct_IdAndWarehouse_Id(Long productId, Long warehouseId);
}
