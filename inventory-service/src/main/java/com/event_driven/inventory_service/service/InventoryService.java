package com.event_driven.inventory_service.service;

import com.event_driven.inventory_service.dto.InventoryUpdatedDto;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId,Integer quantity);

    public InventoryUpdatedDto updateInventory(Long productId,Integer quantity);

    public void reservedInventory(Long productId,Long customerId,Long orderId,Integer quantity);

    public void restoreInventory(Long productId,Long customerId,Long orderId);

    public Integer getInventoryQuantity(Long productId);


}
