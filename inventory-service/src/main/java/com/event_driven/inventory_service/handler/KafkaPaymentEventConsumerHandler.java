package com.event_driven.inventory_service.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPaymentEventConsumerHandler {

    @KafkaListener(topics = "payment-fail-event",groupId="payment-service-group")
    public void consumeFailedEvent(String message){

        // restore the inventory

    }

}
