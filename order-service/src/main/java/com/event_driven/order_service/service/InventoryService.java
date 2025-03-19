package com.event_driven.order_service.service;

import com.event_driven.order_service.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId,Integer quantity) throws ProductNotFoundException;

    public void updateInventory(Long productId,Integer quantity);

}
