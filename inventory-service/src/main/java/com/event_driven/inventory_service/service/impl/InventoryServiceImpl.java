package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.entity.Inventory;
import com.event_driven.inventory_service.repo.InventoryRepository;
import com.event_driven.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;



    @Override
    public boolean isInventoryAvailable(Long productId, Integer quantity) {
        /// get the productInventory
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new RuntimeException("No inventory found"));
        return inventory.getQuantity()>=quantity;
    }
}
