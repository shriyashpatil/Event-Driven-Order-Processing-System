package com.event_driven.inventory_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    @Autowired
    InventoryServiceImpl inventoryService;

    public void consumeOrder(Long productId,Long customerId, Long orderId, Integer quantity){

        Integer currentQuantity = inventoryService.getInventoryQuantity(productId);

        /// TODO : check difference should not be negative
        Integer updatedQuantity = currentQuantity-quantity;

        // update the inventory
        inventoryService.updateInventory(productId,updatedQuantity);

        // reserved the inventory
        inventoryService.reservedInventory(productId,customerId,orderId,quantity);


    }



}
