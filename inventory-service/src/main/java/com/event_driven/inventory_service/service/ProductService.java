package com.event_driven.inventory_service.service;

public interface ProductService {

    public Long addProduct(String name,String details,Double price,Integer quantity);

}
