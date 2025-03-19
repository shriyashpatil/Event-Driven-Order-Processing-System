package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.dto.ApiResponse;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.rest.InventoryServiceClient;
import com.event_driven.order_service.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private static final String INVENTORY_CHECK = "inventoryCheck";

    @Autowired
    InventoryServiceClient inventoryServiceClient;

    @Override
    @Retry(name = INVENTORY_CHECK)
    @CircuitBreaker(name = INVENTORY_CHECK,fallbackMethod = "handleInventoryFallback")
    public boolean isInventoryAvailable(Long productId,Integer quantity) throws ProductNotFoundException {
        try {
            log.debug("Inventory check api for productId {}",productId);
            ApiResponse apiResponse = inventoryServiceClient.checkInventoryAvailable(productId,quantity);
            Object data = apiResponse.getData();
            Boolean status =  (Boolean) data;
            log.info("Inventory check result for productId {}:{}",productId,status);
            return status;
        }catch(Exception e){
            log.error("Inventory check failed for productId {}:{}",productId,e.getMessage());
            return false;
        }
    }

    public boolean handleInventoryFallback(Long productId,Throwable t)
    {
        log.warn("Fallback triggered : Inventory API failed for productId {}:{}",productId,t.getMessage());
        return false;
    }
    @Override
    public void updateInventory(Long productId, Integer quantity) {

    }
}
