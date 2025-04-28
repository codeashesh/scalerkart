package com.scalerkart.ecommerce.services.order.repository;

import com.scalerkart.ecommerce.services.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    List<Order> findByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.id = ?1 AND o.userId = ?2")
    List<Order> findByIdAndUserId(Long id, Long userId);
}