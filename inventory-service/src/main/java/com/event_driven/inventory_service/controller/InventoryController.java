package com.event_driven.inventory_service.controller;

import com.event_driven.inventory_service.dto.InventoryRequestDto;
import com.event_driven.inventory_service.dto.InventoryResponseDto;
import com.event_driven.inventory_service.dto.InventoryUpdatedDto;
import com.event_driven.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> addInventory(@RequestBody InventoryRequestDto inventoryRequestDto){

        return null;
    }

    @PutMapping
    public ResponseEntity<InventoryResponseDto> updateInventory(@RequestBody InventoryRequestDto inventoryRequestDto){

        InventoryUpdatedDto inventoryUpdatedDto = inventoryService.updateInventory(inventoryRequestDto.getProductId(), inventoryRequestDto.getQuantity());

        return new ResponseEntity<>(InventoryResponseDto.builder()
                .data(inventoryUpdatedDto)
                .message("success")
                .build(), HttpStatus.OK);
    }


    @GetMapping("/check/{productId}/{quantity}")
    public ResponseEntity<InventoryResponseDto> checkInventory(@PathVariable("productId") Long productId,@PathVariable("quantity") Integer quantity){
        try {
            Boolean ans = inventoryService.isInventoryAvailable(productId, quantity);
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(ans)
                    .message("Success")
                    .build(), HttpStatus.OK);

        }catch (RuntimeException e){
            return new ResponseEntity<>(InventoryResponseDto.builder()
                    .data(false)
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
