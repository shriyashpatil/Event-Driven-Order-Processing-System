package com.event_driven.order_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponseDto {

    Object orderId;
    String message;


}
