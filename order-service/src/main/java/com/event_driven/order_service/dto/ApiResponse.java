package com.event_driven.order_service.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    HttpStatus status;
    Object data;
    String message;

}
