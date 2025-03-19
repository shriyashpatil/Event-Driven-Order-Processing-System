package com.event_driven.order_service.handler;

import com.event_driven.order_service.dto.PaymentMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PaymentConsumerHandler paymentConsumerHandler;

//    @KafkaListener(topics = "order-event",groupId = "order-service-group")
//    public void consume(String message){
//        System.out.println("consume"+message);
//    }


    public void paymentStatusConsumer(String message){
        try {
            PaymentMessageDto paymentMessageDto = objectMapper.readValue(message,PaymentMessageDto.class);
            paymentConsumerHandler.consume(paymentMessageDto);
        } catch (JsonProcessingException e) {

            //TODO :  log parsing exceptions
            throw new RuntimeException(e);

        }
    }


}
