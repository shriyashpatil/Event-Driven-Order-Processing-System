package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.dto.InventoryUpdatedDto;
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

    @Override
    public InventoryUpdatedDto updateInventory(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new RuntimeException("No inventory found"));
        inventory.setQuantity(quantity);
        inventory=inventoryRepository.save(inventory);

        return InventoryUpdatedDto.builder()
                .inventoryId(inventory.getId())
                .productId(inventory.getProduct().getId())
                .quantity(inventory.getQuantity())
                .build();
    }

    @Override
    public void reservedInventory(Long productId,Long customerId,Long orderId,Integer quantity) {

    }

    @Override
    public void restoreInventory(Long productId,Long customerId,Long orderId) {

    }

    @Override
    public Integer getInventoryQuantity(Long productId) {
        return inventoryRepository.getQuantity(productId).orElseThrow(()->new RuntimeException("Product not found"));
    }


}
