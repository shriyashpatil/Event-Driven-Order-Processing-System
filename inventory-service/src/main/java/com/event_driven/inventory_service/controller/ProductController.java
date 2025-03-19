package com.event_driven.inventory_service.controller;

import com.event_driven.inventory_service.dto.ProductRequestDto;
import com.event_driven.inventory_service.dto.ProductResponseDto;
import com.event_driven.inventory_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        Long productId = productService.addProduct(productRequestDto.getName(), productRequestDto.getDetails(), productRequestDto.getPrice(), productRequestDto.getQuantity());
        return new ResponseEntity<>(ProductResponseDto.builder()
                .message("Success")
                .productId(productId)
                .build(), HttpStatus.OK);
    }

}
