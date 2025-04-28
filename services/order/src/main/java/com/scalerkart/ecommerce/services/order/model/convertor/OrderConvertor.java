package com.scalerkart.ecommerce.services.order.model.convertor;

import com.scalerkart.ecommerce.services.order.model.OrderStatus;
import com.scalerkart.ecommerce.services.order.model.dto.DeliveryAddressDto;
import com.scalerkart.ecommerce.services.order.model.dto.OrderDto;
import com.scalerkart.ecommerce.services.order.model.dto.OrderItemDto;
import com.scalerkart.ecommerce.services.order.model.entity.DeliveryAddress;
import com.scalerkart.ecommerce.services.order.model.entity.Order;
import com.scalerkart.ecommerce.services.order.model.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public interface OrderConvertor {

    static OrderDto toDto(final Order order, final List<OrderItem> items) {
        return OrderDto.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .mobileNumber(order.getMobileNumber())
                .deliveryAddress(toDto(order.getDeliveryAddress()))
                .paymentMethod(order.getPaymentMethod())
                .transactionId(order.getTransactionId())
                .orderStatus(order.getOrderStatus().name())
                .items(items.stream().map(OrderConvertor::toDto).collect(Collectors.toList()))
                .totalAmount(order.getTotalAmount())
                .discount(order.getDiscount())
                .estimatedDelivery(order.getEstimatedDelivery())
                .couponCode(order.getCouponCode())
                .userId(order.getUserId())
                .build();
    }

    static Order fromDto(final OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .customerName(orderDto.getCustomerName())
                .mobileNumber(orderDto.getMobileNumber())
                .deliveryAddress(fromDto(orderDto.getDeliveryAddress()))
                .paymentMethod(orderDto.getPaymentMethod())
                .transactionId(orderDto.getTransactionId())
                .orderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()))
                .totalAmount(orderDto.getTotalAmount())
                .discount(orderDto.getDiscount())
                .estimatedDelivery(orderDto.getEstimatedDelivery())
                .couponCode(orderDto.getCouponCode())
                .userId(orderDto.getUserId())
                .build();
    }

    static DeliveryAddressDto toDto(DeliveryAddress address) {
        return DeliveryAddressDto.builder()
                .name(address.getName())
                .address(address.getAddress())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .mobileNumber(address.getMobileNumber())
                .build();
    }

    static DeliveryAddress fromDto(DeliveryAddressDto addressDto) {
        return DeliveryAddress.builder()
                .name(addressDto.getName())
                .address(addressDto.getAddress())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .pincode(addressDto.getPincode())
                .mobileNumber(addressDto.getMobileNumber())
                .build();
    }

    static OrderItemDto toDto(final OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrderId())
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    static OrderItem fromDto(final OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .id(orderItemDto.getId())
                .orderId(orderItemDto.getOrderId())
                .productId(orderItemDto.getProductId())
                .quantity(orderItemDto.getQuantity())
                .price(orderItemDto.getPrice())
                .build();
    }
}
