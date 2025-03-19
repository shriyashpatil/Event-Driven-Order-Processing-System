package com.event_driven.inventory_service.service;

import com.event_driven.inventory_service.dto.InventoryUpdatedDto;
import com.event_driven.inventory_service.exception.InventoryNotFoundException;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId,Integer quantity) throws InventoryNotFoundException;

    public InventoryUpdatedDto updateInventory(Long productId,Integer quantity) throws InventoryNotFoundException;

    public void reservedInventory(Long productId,Long customerId,Long orderId,Integer quantity);

    public void restoreInventory(Long productId,Long customerId,Long orderId) throws InventoryNotFoundException;

    public Integer getInventoryQuantity(Long productId);


}
