package com.event_driven.inventory_service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="product")
@Data
public class Product extends Base{
    private String name;
    private Double price;
    private String details;
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;
}
