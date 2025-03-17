package com.event_driven.inventory_service.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventConsumerHandler {

    @KafkaListener(topics = "order-event",groupId = "order-service-group")
    public void consumeOrder(String message){
        System.out.println(message);
    }




}
