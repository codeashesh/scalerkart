package com.scalerkart.ecommerce.services.order.service.impl;

import com.scalerkart.ecommerce.services.order.model.convertor.OrderConvertor;
import com.scalerkart.ecommerce.services.order.model.dto.OrderDto;
import com.scalerkart.ecommerce.services.order.model.entity.Order;
import com.scalerkart.ecommerce.services.order.model.entity.OrderItem;
import com.scalerkart.ecommerce.services.order.repository.OrderItemRepository;
import com.scalerkart.ecommerce.services.order.repository.OrderRepository;
import com.scalerkart.ecommerce.services.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

//    @Autowired
//    private ProductClient productClient;


    @Override
    public Boolean existsByOrderId(Long userId, Long orderId) {
        return orderRepository.findById(orderId)
                .filter(order -> order.getUserId() == userId)
                .isPresent();
    }

    @Override
    public List<OrderDto> findByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(order -> Pair.of(order, orderItemRepository.findByOrderId(order.getId())))
                .map(orderPair -> OrderConvertor.toDto(orderPair.getLeft(), orderPair.getRight()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findUserOrders(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(order -> Pair.of(order, orderItemRepository.findByOrderId(order.getId())))
                .map(orderPair -> OrderConvertor.toDto(orderPair.getLeft(), orderPair.getRight()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findUserSpecificOrder(Long userId, Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, userId)
                .stream()
                .findAny()
                .map(order -> Pair.of(order, orderItemRepository.findByOrderId(order.getId())))
                .map(orderPair -> OrderConvertor.toDto(orderPair.getLeft(), orderPair.getRight()))
                .orElse(null);
    }

    @Override
    public OrderDto createCartOrder(OrderDto orderDto) {
        final Order savedOrder = orderRepository.save(OrderConvertor.fromDto(orderDto));
        List<OrderItem> items = orderDto.getItems().stream()
                .peek(dto -> dto.setOrderId(savedOrder.getId()))
                .map(OrderConvertor::fromDto)
                .collect(Collectors.toList());
        List<OrderItem> savedItems = orderItemRepository.saveAll(items);
        return OrderConvertor.toDto(savedOrder, savedItems);
        // TODO: Delete cart
    }

    @Override
    public OrderDto createProductOrder(OrderDto orderDto) {
        final Order savedOrder = orderRepository.save(OrderConvertor.fromDto(orderDto));
        List<OrderItem> items = orderDto.getItems().stream()
                .peek(dto -> dto.setOrderId(savedOrder.getId()))
                .map(OrderConvertor::fromDto)
                .collect(Collectors.toList());
        List<OrderItem> savedItems = orderItemRepository.saveAll(items);
        return OrderConvertor.toDto(savedOrder, savedItems);
    }

    @Override
    public OrderDto modifyOrder(OrderDto orderDto) {
        final Order savedOrder = orderRepository.save(OrderConvertor.fromDto(orderDto));
        List<OrderItem> items = orderDto.getItems().stream()
                .peek(dto -> dto.setOrderId(savedOrder.getId()))
                .map(OrderConvertor::fromDto)
                .collect(Collectors.toList());
        List<OrderItem> savedItems = orderItemRepository.saveAll(items);
        return OrderConvertor.toDto(savedOrder, savedItems);
    }

    @Override
    public void cancelUserSpecificOrder(Long userId, Long orderId) {
        final OrderDto existingOrder = findUserSpecificOrder(userId, orderId);
        if (existingOrder != null) {
            orderItemRepository.deleteById(existingOrder.getId());
            orderRepository.deleteById(existingOrder.getId());
        }
    }

}
