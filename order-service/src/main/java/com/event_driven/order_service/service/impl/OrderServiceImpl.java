package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.entity.Order;
import com.event_driven.order_service.entity.OrderStatus;
import com.event_driven.order_service.exceptions.OrderNotFoundException;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.exceptions.ProductOutOfStockException;
import com.event_driven.order_service.helper.OrderAsyncHelper;
import com.event_driven.order_service.repo.OrderRepository;
import com.event_driven.order_service.service.InventoryService;
import com.event_driven.order_service.service.OrderService;
import com.event_driven.order_service.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderAsyncHelper orderAsyncHelper;




    @Override
    public Long createOrder(Long customerId, Long productId, Integer quantity, Double price) throws ProductNotFoundException,JsonProcessingException,ProductOutOfStockException{


        //check if inventory is out of stock
       if(!inventoryService.isInventoryAvailable(productId,quantity)) throw new ProductOutOfStockException("Product out of stock : "+productId);

        // create order
        Order order = new Order();

        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setPrice(price);
        order.setQuantity(quantity);
        order.setOrderStatus(OrderStatus.PROCESSING);

        //save order
        order = orderRepository.save(order);

        // once order is created publish and event in the kafka
        orderAsyncHelper.produceOrderEvent(order);

        return order.getId();
    }



    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("no order found by this Id : "+orderId));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }



}
