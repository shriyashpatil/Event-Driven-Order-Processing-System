package com.event_driven.inventory_service.handler;

import com.event_driven.inventory_service.service.impl.InventoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventConsumerHandler {
    private static final Logger log = LoggerFactory.getLogger(KafkaOrderEventConsumerHandler.class);


    private final EventHandler eventHandler;

    @Autowired
    public KafkaOrderEventConsumerHandler(@Qualifier("orderEventHandler") EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @KafkaListener(topics = "order-event",groupId = "order-service-group",concurrency = "10")
    public void consumeOrder(String message){
        eventHandler.consume(message);
    }




}
