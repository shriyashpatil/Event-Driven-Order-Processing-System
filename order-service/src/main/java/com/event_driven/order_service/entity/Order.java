package com.event_driven.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order extends Base{

    private Long customerId; // fetched from user service

    private Long productId; // fetched from inventory service

    private Integer quantity;

    private OrderStatus orderStatus;

    private Double price;

}
