package com.event_driven.order_service.service;

import com.event_driven.order_service.entity.Order;
import com.event_driven.order_service.entity.OrderStatus;
import com.event_driven.order_service.exceptions.OrderNotFoundException;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.exceptions.ProductOutOfStockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    Long createOrder(Long customerId,Long productId,Integer quantity,Double price)throws ProductNotFoundException, JsonProcessingException, ProductOutOfStockException;

    Order getOrder(Long orderId);

    List<Order> getOrders();

    void updateOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderNotFoundException;
}
