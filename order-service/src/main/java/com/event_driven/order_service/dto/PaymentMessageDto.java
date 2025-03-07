package com.event_driven.order_service.dto;

import lombok.Data;

@Data
public class PaymentMessageDto {

    String paymentStatus;
    Long orderId;

}
