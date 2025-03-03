package com.event_driven.order_service.service.impl;

import com.event_driven.order_service.entity.Order;
import com.event_driven.order_service.entity.OrderStatus;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.repo.OrderRepository;
import com.event_driven.order_service.service.OrderService;
import com.event_driven.order_service.service.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Long createOrder(Long customerId, Long productId, Integer quantity, Double price) throws ProductNotFoundException {

        //check if customer is present from user service : no need tocheck here as it will be check in api gateway it self

        // check if product is present from product service
        if(!productService.isProductAvailable(productId)) throw new ProductNotFoundException("Product not found: "+productId);

        // create order
        Order order = new Order();

        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setPrice(price);
        order.setOrderStatus(OrderStatus.PROCESSING);

        //save order
        order = orderRepository.save(order);

        // once order is created publish and event in the kafka


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
}
