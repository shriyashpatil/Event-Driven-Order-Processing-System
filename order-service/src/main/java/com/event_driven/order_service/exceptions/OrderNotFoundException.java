package com.event_driven.order_service.exceptions;

public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String message){
        super(message);
    }
}
