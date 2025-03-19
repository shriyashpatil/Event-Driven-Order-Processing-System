package com.event_driven.order_service.rest;

import com.event_driven.order_service.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service",url = "${inventory.service.url}")
public interface InventoryServiceClient {

    @GetMapping("/check/{productId}/{quantity}")
    ApiResponse checkInventoryAvailable(@PathVariable("productId") Long id,@PathVariable("quantity") Integer quantity);


}
