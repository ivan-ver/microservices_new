package com.programming.inventoryservice.service;

import com.programming.inventoryservice.dto.InventoryResponseDto;
import com.programming.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStockIn(List<String> skuCode) {
        List<InventoryResponseDto> inventoryResponseDtos = inventoryRepository.findAllBySkuCode(skuCode)
                .stream().map(item -> {
                    InventoryResponseDto build = InventoryResponseDto.builder()
                            .skuCode(item.getSkuCode())
                            .isInStock(item.getQuantity() > 0)
                            .build();
                    return build;
                }).toList();

        return inventoryResponseDtos;
    }
}
