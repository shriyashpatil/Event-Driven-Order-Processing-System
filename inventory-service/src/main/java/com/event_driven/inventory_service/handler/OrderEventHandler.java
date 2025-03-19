package com.event_driven.inventory_service.handler;

import com.event_driven.inventory_service.dto.OrderEvent;
import com.event_driven.inventory_service.exception.InventoryNotFoundException;
import com.event_driven.inventory_service.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("orderEventHandler")
public class OrderEventHandler implements EventHandler{

    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    @Autowired
    OrderService orderService;

    public void consume(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        OrderEvent order=null;
        try {
            order = objectMapper.readValue(message,OrderEvent.class);
            orderService.consumeOrder(order.getProductId(), order.getCustomerId(), order.getOrderId(), order.getQuantity());
        } catch (JsonProcessingException e) {
            log.error("Failed to parse the order message: {}. Error: {}", message, e.getMessage());
        }catch(InventoryNotFoundException e){
            log.error("Inventory not found for Product ID: {}. Order ID: {}. Customer ID: {}",
                    order.getProductId(), order.getOrderId(), order.getCustomerId());
        }

    }


}
