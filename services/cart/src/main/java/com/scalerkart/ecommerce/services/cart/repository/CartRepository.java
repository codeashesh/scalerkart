package com.scalerkart.ecommerce.services.cart.repository;

import com.scalerkart.ecommerce.services.cart.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

}