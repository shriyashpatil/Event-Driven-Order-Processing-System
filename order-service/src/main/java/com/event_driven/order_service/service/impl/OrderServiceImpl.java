package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.dto.OrderEvent;
import com.event_driven.order_service.entity.Order;
import com.event_driven.order_service.entity.OrderStatus;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.exceptions.ProductOutOfStockException;
import com.event_driven.order_service.handler.KafkaProducerHandler;
import com.event_driven.order_service.repo.OrderRepository;
import com.event_driven.order_service.service.InventoryService;
import com.event_driven.order_service.service.OrderService;
import com.event_driven.order_service.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaProducerHandler kafkaProducerHandler;


    @Override
    public Long createOrder(Long customerId, Long productId, Integer quantity, Double price) throws ProductNotFoundException,JsonProcessingException,ProductOutOfStockException{

        // check if product is present from product service
        if(!productService.isProductAvailable(productId)) throw new ProductNotFoundException("Product not found: "+productId);

        //check if inventory is out of stock
        if(!inventoryService.isInventoryAvailable(productId)) throw new ProductOutOfStockException("Product out of stock : "+productId);

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
        produceEvent(order);

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
    public boolean updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        return false;
    }

    @Async
    private void produceEvent(Order order) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setCustomerId(order.getCustomerId());
        orderEvent.setProductId(order.getProductId());
        orderEvent.setOrderId(order.getId());
        orderEvent.setPrice(order.getPrice());
        orderEvent.setQuantity(order.getQuantity());

        String event = objectMapper.writeValueAsString(orderEvent);

        kafkaProducerHandler.produce("order-event",event);

    }


}
