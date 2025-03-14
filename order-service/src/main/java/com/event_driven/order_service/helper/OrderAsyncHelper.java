package com.event_driven.order_service.helper;


import com.event_driven.order_service.dto.OrderEvent;
import com.event_driven.order_service.entity.Order;
import com.event_driven.order_service.handler.KafkaProducerHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.security.KeyException;

@Component
public class OrderAsyncHelper {
    private static final Logger log = LoggerFactory.getLogger(OrderAsyncHelper.class);
    @Autowired
    KafkaProducerHandler kafkaProducerHandler;


    @Async
    public void produceOrderEvent(Order order) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setCustomerId(order.getCustomerId());
        orderEvent.setProductId(order.getProductId());
        orderEvent.setOrderId(order.getId());
        orderEvent.setPrice(order.getPrice());
        orderEvent.setQuantity(order.getQuantity());

        String event = objectMapper.writeValueAsString(orderEvent);
        log.debug("Publishing Kafka event for Order ID: {}", order.getId());
        try {
            kafkaProducerHandler.produce("order-event",event);
        } catch (KeyException e) {
            log.error("Publishing Event failed for Order ID:{}",order.getId());
        }
    }


}
