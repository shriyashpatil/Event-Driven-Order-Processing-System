package com.event_driven.order_service.service;

import org.springframework.stereotype.Service;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId);

    public void updateInventory(Long productId,Integer quantity);

}
