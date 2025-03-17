package com.event_driven.inventory_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryUpdatedDto {

    Long productId;
    Long inventoryId;
    Integer quantity;

}
