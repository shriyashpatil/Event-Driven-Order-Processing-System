package com.event_driven.order_service.dto;

import lombok.Builder;

@Builder
public class OrderResponseDto {

    Object orderId;
    String message;


}
