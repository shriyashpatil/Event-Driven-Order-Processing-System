package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.entity.Inventory;
import com.event_driven.inventory_service.entity.Product;
import com.event_driven.inventory_service.repo.ProductRepository;
import com.event_driven.inventory_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;

    @Override
    public Long addProduct(String name, String details, Double price,Integer quantity) {
        //set object;
        Product p = new Product();
        p.setDetails(details);
        p.setName(name);
        p.setPrice(price);
        p.setInventory(setInventory(quantity,p));
        p = productRepository.save(p);
        return p.getId();

    }

    private Inventory setInventory(Integer quantity,Product p){
        Inventory inventory = new Inventory();
        inventory.setProduct(p);
        inventory.setQuantity(quantity);
        return inventory;
    }


}
