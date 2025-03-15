package com.event_driven.inventory_service.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

    String name;
    String details;
    Long price;

}
