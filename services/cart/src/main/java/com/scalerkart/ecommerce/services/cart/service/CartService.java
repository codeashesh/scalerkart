package com.scalerkart.ecommerce.services.cart.service;

import com.scalerkart.ecommerce.services.cart.model.dto.CartDto;
import com.scalerkart.ecommerce.services.cart.model.dto.CartItemDto;

import java.util.List;

public interface CartService {
    List<CartDto> findAll();

    CartDto findByUser(Long userId);

    List<CartItemDto> findItems(Long userId);

    CartDto addItemToCart(Long userId, CartItemDto itemDto);

    CartDto updateItemInCart(Long userId, CartItemDto itemDto);

    CartDto removeItemInCart(Long userId, Long cartItemId);

    CartDto removeAllItemInCart(Long userId);
}
