package com.event_driven.inventory_service.controller;

import com.event_driven.inventory_service.dto.InventoryRequestDto;
import com.event_driven.inventory_service.dto.InventoryResponseDto;
import com.event_driven.inventory_service.dto.InventoryUpdatedDto;
import com.event_driven.inventory_service.exception.InventoryNotFoundException;
import com.event_driven.inventory_service.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> addInventory(@RequestBody InventoryRequestDto inventoryRequestDto){

        return null;
    }

    @PutMapping
    public ResponseEntity<InventoryResponseDto> updateInventory(@RequestBody InventoryRequestDto inventoryRequestDto){
        try {
            InventoryUpdatedDto inventoryUpdatedDto = inventoryService.updateInventory(inventoryRequestDto.getProductId(), inventoryRequestDto.getQuantity());
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(inventoryUpdatedDto)
                    .message("success")
                    .build(), HttpStatus.OK);
        }catch(InventoryNotFoundException e){
            log.error("Inventory not found for Product ID: {}", inventoryRequestDto.getProductId(), e);
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(null)
                    .message("Failed: Inventory not found for the specified product.")
                    .build(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/check/{productId}/{quantity}")
    public ResponseEntity<InventoryResponseDto> checkInventory(@PathVariable("productId") Long productId,@PathVariable("quantity") Integer quantity){
        try {
            Boolean ans = inventoryService.isInventoryAvailable(productId, quantity);
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(ans)
                    .message("Success")
                    .build(), HttpStatus.OK);

        }catch (InventoryNotFoundException e){
            log.error("Inventory not found for Product ID: {}", productId, e);
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(false)
                    .message(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }


}
