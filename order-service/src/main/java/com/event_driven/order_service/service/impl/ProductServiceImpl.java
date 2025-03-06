package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public boolean isProductAvailable(Long productId) {

        // check if product is available

        return true;
    }
}
