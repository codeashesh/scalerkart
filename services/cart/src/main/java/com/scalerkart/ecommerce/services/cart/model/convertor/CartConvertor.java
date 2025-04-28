package com.scalerkart.ecommerce.services.cart.model.convertor;

import com.scalerkart.ecommerce.services.cart.model.dto.CartDto;
import com.scalerkart.ecommerce.services.cart.model.dto.CartItemDto;
import com.scalerkart.ecommerce.services.cart.model.entity.Cart;
import com.scalerkart.ecommerce.services.cart.model.entity.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public interface CartConvertor {
    static CartDto toDto(final Cart cart, final List<CartItem> items) {
        if (cart == null) return null;
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(items
                        .stream()
                        .map(item -> CartItemDto.builder()
                                .cartItemId(item.getId())
                                .cartId(item.getCartId())
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    static Cart fromDto(final CartDto cartDto) {
        if (cartDto == null) return null;
        return Cart.builder()
                .id(cartDto.getId())
                .userId(cartDto.getUserId())
                .build();
    }

    static CartItem fromDto(final CartItemDto itemDto) {
        if (itemDto == null) return null;
        return CartItem.builder()
                .id(itemDto.getCartItemId())
                .cartId(itemDto.getCartId())
                .productId(itemDto.getProductId())
                .quantity(itemDto.getQuantity())
                .price(itemDto.getPrice())
                .build();
    }
}
