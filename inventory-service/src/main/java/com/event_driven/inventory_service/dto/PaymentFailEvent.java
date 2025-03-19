package com.event_driven.inventory_service.dto;

import lombok.Data;

@Data
public class PaymentFailEvent {
    Long productId;
    Long customerId;
    Long orderId;
}
