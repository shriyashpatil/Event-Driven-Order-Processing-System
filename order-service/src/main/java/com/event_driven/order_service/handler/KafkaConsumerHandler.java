package com.event_driven.order_service.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerHandler {


//    @KafkaListener(topics = "order-event",groupId = "order-service-group")
//    public void consume(String message){
//        System.out.println("consume"+message);
//    }

}
