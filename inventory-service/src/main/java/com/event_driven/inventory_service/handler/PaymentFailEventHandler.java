package com.event_driven.inventory_service.handler;

import com.event_driven.inventory_service.dto.PaymentFailEvent;
import com.event_driven.inventory_service.exception.InventoryNotFoundException;
import com.event_driven.inventory_service.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("paymentFailEventHandler")
@Component
public class PaymentFailEventHandler implements EventHandler{

    private static final Logger log = LoggerFactory.getLogger(PaymentFailEventHandler.class);


    @Autowired
    InventoryService inventoryService;

    @Override
    public void consume(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentFailEvent paymentFailEvent=null;
        try {
            paymentFailEvent = objectMapper.readValue(message, PaymentFailEvent.class);
            inventoryService.restoreInventory(paymentFailEvent.getProductId(),
                    paymentFailEvent.getCustomerId(),
                    paymentFailEvent.getOrderId());
            log.info("Inventory successfully restored for Product ID: {}, Order ID: {}, Customer ID: {}",
                    paymentFailEvent.getProductId(),
                    paymentFailEvent.getOrderId(),
                    paymentFailEvent.getCustomerId());
        } catch (JsonProcessingException e) {
            log.error("Failed to parse PaymentFailEvent message: {}. Error: {}", message, e.getMessage());
        }catch (InventoryNotFoundException e){
            log.error("Inventory not found while attempting to restore. Product ID: {}, Order ID: {}, Customer ID: {}",
                    paymentFailEvent.getProductId(),
                    paymentFailEvent.getOrderId(),
                    paymentFailEvent.getCustomerId());
        }

    }
}
