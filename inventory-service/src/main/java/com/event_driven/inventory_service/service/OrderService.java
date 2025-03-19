package com.event_driven.inventory_service.service;

import com.event_driven.inventory_service.exception.InventoryNotFoundException;

public interface OrderService {
    public void consumeOrder(Long productId,Long customerId, Long orderId, Integer quantity) throws InventoryNotFoundException;
}
