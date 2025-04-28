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
@Table(name = "cart")
public final class Cart extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId; // userId

}
