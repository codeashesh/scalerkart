package com.scalerkart.ecommerce.services.cart.repository;

import com.scalerkart.ecommerce.services.cart.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cartId = ?1 AND ci.productId = ?2")
    List<CartItem> findByCartIdAndProductId(Long cartId, String productId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cartId = ?1")
    void deleteByCartId(Long cartId);

    @Modifying
    @Query("UPDATE CartItem ci SET ci.quantity = ?1 WHERE ci.productId = ?2 AND ci.id = ?3")
    void update(Integer quantity, String productId, Long id);
}