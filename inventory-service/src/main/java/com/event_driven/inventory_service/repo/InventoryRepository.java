package com.event_driven.inventory_service.repo;

import com.event_driven.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{

    @Query(value = "select * from inventory where product_id=?1",nativeQuery = true)
    Optional<Inventory> findByProductId(Long productId);

    @Query(value="select quantity from inventory where product_id=?1",nativeQuery = true)
    Optional<Integer> getQuantity(Long productId);

}
