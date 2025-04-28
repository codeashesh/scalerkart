package com.scalerkart.ecommerce.services.cart.service.impl;

import com.scalerkart.ecommerce.services.cart.exception.wrapper.CartNotFoundException;
import com.scalerkart.ecommerce.services.cart.model.convertor.CartConvertor;
import com.scalerkart.ecommerce.services.cart.model.dto.CartDto;
import com.scalerkart.ecommerce.services.cart.model.dto.CartItemDto;
import com.scalerkart.ecommerce.services.cart.model.entity.Cart;
import com.scalerkart.ecommerce.services.cart.repository.CartItemRepository;
import com.scalerkart.ecommerce.services.cart.repository.CartRepository;
import com.scalerkart.ecommerce.services.cart.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartDto> findAll() {
        log.info("CartDto List, service; fetch all carts");
        return getAllCarts();
    }

    @Override
    public CartDto findByUser(Long userId) {
        log.info("CartDto List, service; fetch all carts");
        Optional<CartDto> cartOptional = getCartByUser(userId);
        if (cartOptional.isPresent()) {
            return cartOptional.get();
        }
        cartRepository.save(Cart.builder().userId(userId).build());
        return getCartByUser(userId).orElseThrow(() -> new CartNotFoundException("Problem fetching cart"));
    }

    @Override
    public List<CartItemDto> findItems(Long userId) {
        log.info("CartDto, service; fetch cart by id");
        return getCartByUser(userId)
                .map(CartDto::getItems)
                .orElseThrow(() -> new CartNotFoundException("Problem fetching cart"));
    }

    @Override
    public CartDto addItemToCart(Long userId, final CartItemDto itemDto) {
        log.info("CartDto, service; save cart");
        Long cartId = findByUser(userId).getId();
        itemDto.setCartId(cartId);
        var carts = cartItemRepository.findByCartIdAndProductId(cartId, itemDto.getProductId());
        if(carts.isEmpty()) {
            cartItemRepository.save(CartConvertor.fromDto(itemDto));
        } else {
            var cart = carts.get(0);
            cart.setPrice(itemDto.getPrice());
            cart.setQuantity(cart.getQuantity()+itemDto.getQuantity());
            cart.setUpdatedAt(Instant.now());
            cartItemRepository.save(cart);
        }
        return findByUser(userId);
    }

    private Long getCartId(Long userId) {
        var carts = cartRepository.findByUserId(userId);
        if (carts.isEmpty()) {
            throw new CartNotFoundException("Problem fetching cart");
        }
        if (carts.size() > 1) {
            throw new CartNotFoundException("Invalid request");
        }
        return carts.get(0).getId();
    }

    @Override
    public CartDto updateItemInCart(Long userId, final CartItemDto itemDto) {
        log.info("CartDto, service; update cart");
        cartItemRepository.update(itemDto.getQuantity(), itemDto.getProductId(), itemDto.getCartItemId());
        return findByUser(userId);
    }

    @Override
    public CartDto removeItemInCart(Long userId, Long cartItemId) {
        log.info("CartDto, service; update cart");
        cartItemRepository.deleteById(cartItemId);
        return findByUser(userId);
    }

    @Override
    public CartDto removeAllItemInCart(Long userId) {
        log.info("CartDto, service; update cart");
        cartItemRepository.deleteById(getCartId(userId));
        return findByUser(userId);
    }

    private List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cart -> CartConvertor.toDto(cart, cartItemRepository.findByCartId(cart.getId())))
                .toList();
    }

    private Optional<CartDto> getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(cart -> CartConvertor.toDto(cart, cartItemRepository.findByCartId(cart.getId())))
                .findAny();
    }

}
