package com.event_driven.order_service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerHandler {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void produce(String topic, String event){
        kafkaTemplate.send(topic,event);
    }

}
