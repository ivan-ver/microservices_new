package com.programming.ordersevice.service;

import com.programming.ordersevice.dto.OrderRequestDto;
import com.programming.ordersevice.model.Order;
import com.programming.ordersevice.model.OrderLineItems;
import com.programming.ordersevice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeService(OrderRequestDto orderRequestDto) {
        Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).build();

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemDtoList().stream()
                .map(item -> OrderLineItems.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .skuCode(item.getSkuCode())
                        .build()).toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }
}
