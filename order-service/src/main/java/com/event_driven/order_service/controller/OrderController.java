package com.event_driven.order_service.controller;

import com.event_driven.order_service.dto.OrderRequestDto;
import com.event_driven.order_service.dto.OrderResponseDto;
import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.exceptions.ProductOutOfStockException;
import com.event_driven.order_service.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    //create an order
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto order){
        try {
            Long orderId = orderService.createOrder(order.getCustomerId(),
                    order.getProductId(),
                    order.getQuantity(),
                    order.getPrice());

            return new ResponseEntity<>(OrderResponseDto.builder()
                    .orderId(orderId)
                    .message("successfully order created")
                    .build(), HttpStatus.OK);

        }catch (ProductNotFoundException | ProductOutOfStockException pne){
            return new ResponseEntity<>(OrderResponseDto.builder()
                    .message(pne.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(OrderResponseDto.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get order by id

    //get all orders

    // update order status

}
