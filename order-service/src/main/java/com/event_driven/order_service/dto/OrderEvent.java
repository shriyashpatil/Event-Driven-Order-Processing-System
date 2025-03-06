package com.event_driven.order_service.dto;

import lombok.Data;

@Data
public class OrderEvent {

    private Long orderId;
    private Long customerId;
    private Long productId;

    private Double price;
    private Integer quantity;
}
