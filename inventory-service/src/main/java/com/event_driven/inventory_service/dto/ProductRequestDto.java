package com.event_driven.inventory_service.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private String details;
    private Double price;
    private Integer quantity;
}
