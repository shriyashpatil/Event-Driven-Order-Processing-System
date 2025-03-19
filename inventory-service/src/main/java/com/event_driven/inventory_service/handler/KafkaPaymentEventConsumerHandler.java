package com.event_driven.inventory_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPaymentEventConsumerHandler {

    private static final Logger log = LoggerFactory.getLogger(KafkaPaymentEventConsumerHandler.class);


    private final EventHandler eventHandler;

    @Autowired
    public KafkaPaymentEventConsumerHandler(@Qualifier("paymentFailEventHandler") EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @KafkaListener(topics = "payment-fail-event",groupId="payment-service-group",concurrency = "10")
    public void consumeFailedEvent(String message){
        eventHandler.consume(message);
    }

}
