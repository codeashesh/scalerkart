package com.scalerkart.ecommerce.services.cart.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cart_item")
public final class CartItem extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", columnDefinition = "decimal")
    private Double price;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "cart_id")
    private Long cartId;

}

