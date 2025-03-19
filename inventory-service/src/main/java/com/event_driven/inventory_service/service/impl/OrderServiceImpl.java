package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.exception.InventoryNotFoundException;
import com.event_driven.inventory_service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    InventoryServiceImpl inventoryService;

    public void consumeOrder(Long productId,Long customerId, Long orderId, Integer quantity) throws InventoryNotFoundException {

        updateInventory(productId,quantity);
        // reserved the inventory
        inventoryService.reservedInventory(productId,customerId,orderId,quantity);

    }


    private void updateInventory(Long productId, Integer quantity) throws InventoryNotFoundException {
        Integer currentQuantity = inventoryService.getInventoryQuantity(productId);
        /// TODO : check difference should not be negative
        Integer updatedQuantity = currentQuantity-quantity;

        log.info("updated quantity :{}",updatedQuantity);
        // update the inventory
        inventoryService.updateInventory(productId,updatedQuantity);
    }



}
