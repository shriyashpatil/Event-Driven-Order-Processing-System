package com.event_driven.order_service.service;

import com.event_driven.order_service.exceptions.ProductNotFoundException;
import com.event_driven.order_service.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class InventoryServiceTest {

    @Mock
    private InventoryServiceImpl inventoryService;


    @Test
    void testCheckInventoryAvailable_WithRetry() throws ProductNotFoundException {

        // Mock behavior to throw an exception initially and then return true
        Mockito.when(inventoryService.isInventoryAvailable(10L))
                .thenThrow(new RuntimeException("failure"))
                .thenReturn(true);  // Simulating retry success

        // Capture the return value
        boolean result = inventoryService.isInventoryAvailable(10L);

        // Verify and assert
        assertTrue(result); // Expected true after retry
        verify(inventoryService, times(2)).isInventoryAvailable(10L);

    }



}
