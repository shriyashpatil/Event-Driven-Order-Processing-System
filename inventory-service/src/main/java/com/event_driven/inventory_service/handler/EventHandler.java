package com.event_driven.inventory_service.handler;

public interface EventHandler {
    public void consume(String message);
}
