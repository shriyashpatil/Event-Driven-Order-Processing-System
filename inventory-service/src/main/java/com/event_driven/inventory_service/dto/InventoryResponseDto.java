package com.event_driven.inventory_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDto {

    Object data;
    String message;

}
