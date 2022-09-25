package com.programming.ordersevice.controller;

import com.programming.ordersevice.dto.OrderRequestDto;
import com.programming.ordersevice.service.allProductsInStock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final allProductsInStock orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            orderService.placeService(orderRequestDto);
            return "success";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
