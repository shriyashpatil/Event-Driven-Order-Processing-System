package com.event_driven.inventory_service.exception;

public class InventoryNotFoundException extends Exception{

    public InventoryNotFoundException(String message){
        super(message);
    }

}
