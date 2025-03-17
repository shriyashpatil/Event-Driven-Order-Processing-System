package com.event_driven.inventory_service.service;

import com.event_driven.inventory_service.dto.InventoryUpdatedDto;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId,Integer quantity);

    public InventoryUpdatedDto updateInventory(Long productId,Integer quantity);


}
