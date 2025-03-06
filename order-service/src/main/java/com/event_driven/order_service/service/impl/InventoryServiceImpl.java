package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public boolean isInventoryAvailable(Long productId) {
        /// check is inventory available for this product
        return true;
    }

    @Override
    public void updateInventory(Long productId, Integer quantity) {

    }
}
