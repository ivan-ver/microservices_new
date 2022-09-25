package com.programming.inventoryservice.controller;

import com.programming.inventoryservice.dto.InventoryResponseDto;
import com.programming.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStockIn(skuCode);
    }
}
