package com.event_driven.inventory_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="inventory")
@Data
public class Inventory extends Base {
    @OneToOne
    private Product product;
    private Integer quantity;
}
