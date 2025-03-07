package com.event_driven.order_service.handler;

import com.event_driven.order_service.dto.PaymentMessageDto;
import com.event_driven.order_service.entity.OrderStatus;
import com.event_driven.order_service.exceptions.OrderNotFoundException;
import com.event_driven.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumerHandler {

    @Autowired
    OrderService orderService;

    public void consume(PaymentMessageDto paymentMessageDto){
        Long orderId = paymentMessageDto.getOrderId();
        OrderStatus orderStatus = paymentMessageDto.getPaymentStatus().equalsIgnoreCase("Success")?OrderStatus.COMPLETED:OrderStatus.CANCELLED;
        try {
            orderService.updateOrderStatus(orderId, orderStatus);
        }catch(OrderNotFoundException oe){
            // TODO : LOG EXCEPTION
        }
    }



}
