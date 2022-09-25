package com.programming.ordersevice.service;

import com.programming.ordersevice.dto.InventoryResponseDto;
import com.programming.ordersevice.dto.OrderRequestDto;
import com.programming.ordersevice.model.Order;
import com.programming.ordersevice.model.OrderLineItems;
import com.programming.ordersevice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class allProductsInStock {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeService(OrderRequestDto orderRequestDto) throws IllegalAccessException {
        Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).build();

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemDtoList().stream()
                .map(item -> OrderLineItems.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .skuCode(item.getSkuCode())
                        .build()).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();


        InventoryResponseDto[] inventoryResponseArray = webClient.get()
                .uri(
                        "http://localhost:8183/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allMatch = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponseDto::isInStock);

        if (allMatch){
            orderRepository.save(order);
        } else {
            throw new IllegalAccessException("Product is not in a stock");
        }

    }
}
