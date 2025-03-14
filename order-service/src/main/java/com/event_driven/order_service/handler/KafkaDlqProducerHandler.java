package com.event_driven.order_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaDlqProducerHandler implements KafkaProducer {


    private static final Logger log = LoggerFactory.getLogger(KafkaDlqProducerHandler.class);

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void produce(String topic, String event){
        try {
            kafkaTemplate.send(topic, event).get(); // Ensures sync delivery for DLQ
            log.info("Event successfully sent to DLQ: {}", event);
        } catch (Exception e) {
            log.error("Failed to send event to DLQ: {}", event, e);
            // Optional: Store in DB or notify relevant teams
        }
    }

}
