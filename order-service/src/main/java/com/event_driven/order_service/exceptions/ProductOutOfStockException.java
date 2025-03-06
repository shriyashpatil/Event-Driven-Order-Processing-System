package com.event_driven.order_service.exceptions;

public class ProductOutOfStockException extends Exception{

    public  ProductOutOfStockException(String message){
        super(message);
    }
}
