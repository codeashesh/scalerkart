package com.scalerkart.ecommerce.services.order.service;

import com.scalerkart.ecommerce.services.order.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Boolean existsByOrderId(Long userId, Long orderId);

    List<OrderDto> findByUser(Long userId);

    List<OrderDto> findUserOrders(Long userId);

    OrderDto findUserSpecificOrder(Long userId, Long orderId);

    OrderDto createCartOrder(OrderDto orderDto);

    OrderDto createProductOrder(OrderDto orderDto);

    OrderDto modifyOrder(OrderDto orderDto);

    void cancelUserSpecificOrder(Long userId, Long orderId);
}
