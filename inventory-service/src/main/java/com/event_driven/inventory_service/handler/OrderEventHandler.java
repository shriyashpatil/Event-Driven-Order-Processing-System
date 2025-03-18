package com.event_driven.inventory_service.handler;

import com.event_driven.inventory_service.dto.OrderEvent;
import com.event_driven.inventory_service.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @Autowired
    OrderService orderService;

    public void consume(String message){

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            OrderEvent order = objectMapper.readValue(message,OrderEvent.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
