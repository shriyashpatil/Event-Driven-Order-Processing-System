package com.event_driven.order_service.dto;


import lombok.Data;

@Data
public class OrderRequestDto {

    Long customerId;
    Long productId;
    Integer quantity;
    Double price;

}
