package com.event_driven.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryRequestDto {
    Long productId;
    Integer quantity;
}
