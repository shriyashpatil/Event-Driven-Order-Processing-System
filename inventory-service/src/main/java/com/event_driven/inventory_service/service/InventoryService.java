package com.event_driven.inventory_service.service;

public interface InventoryService {

    public boolean isInventoryAvailable(Long productId,Integer quantity);


}
