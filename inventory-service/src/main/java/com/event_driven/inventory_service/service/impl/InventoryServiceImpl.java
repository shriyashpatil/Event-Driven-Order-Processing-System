package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.controller.ProductController;
import com.event_driven.inventory_service.dto.InventoryUpdatedDto;
import com.event_driven.inventory_service.entity.Inventory;
import com.event_driven.inventory_service.exception.InventoryNotFoundException;
import com.event_driven.inventory_service.repo.InventoryRepository;
import com.event_driven.inventory_service.service.CacheService;
import com.event_driven.inventory_service.service.InventoryService;
import com.event_driven.inventory_service.util.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);


    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    CacheService cacheService;



    @Override
    public boolean isInventoryAvailable(Long productId, Integer quantity) throws InventoryNotFoundException {
        /// get the productInventory
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException("No inventory found"));
        return inventory.getQuantity()>=quantity;
    }

    @Override
    public InventoryUpdatedDto updateInventory(Long productId, Integer quantity) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException("No inventory found"));
        inventory.setQuantity(quantity);
        inventory=inventoryRepository.save(inventory);
        return InventoryUpdatedDto.builder()
                .inventoryId(inventory.getId())
                .productId(inventory.getProduct().getId())
                .quantity(inventory.getQuantity())
                .build();
    }

    @Override
    public void reservedInventory(Long productId,Long customerId,Long orderId,Integer quantity) {
        String key = KeyUtil.convertToKey(productId, customerId, orderId);
        cacheService.setData(key,String.valueOf(quantity),15);
    }

    @Override
    public void restoreInventory(Long productId,Long customerId,Long orderId) throws InventoryNotFoundException {
        String key = KeyUtil.convertToKey(productId, customerId, orderId);
        String value = cacheService.getData(key);
        Integer reservedQuantity = Integer.valueOf(value);
        Integer quantity = getInventoryQuantity(productId);
        Integer updatedQuantity = reservedQuantity+quantity;
        updateInventory(productId,updatedQuantity);
    }

    @Override
    public Integer getInventoryQuantity(Long productId) {
        return inventoryRepository.getQuantity(productId).orElseThrow(()->new RuntimeException("Product not found"));
    }


}
