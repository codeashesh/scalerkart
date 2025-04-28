package com.scalerkart.ecommerce.services.order.repository;

import com.scalerkart.ecommerce.services.order.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderId = ?1")
    List<OrderItem> findByOrderId(Long orderId);

    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.orderId = ?1")
    List<OrderItem> deleteByOrderId(Long orderId);

}