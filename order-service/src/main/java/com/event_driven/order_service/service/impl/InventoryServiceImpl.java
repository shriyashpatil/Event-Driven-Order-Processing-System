package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.service.InventoryService;
import org.springframework.stereotype.Service;

@Service
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
