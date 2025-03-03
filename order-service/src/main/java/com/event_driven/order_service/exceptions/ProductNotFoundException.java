package com.event_driven.order_service.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }
}
