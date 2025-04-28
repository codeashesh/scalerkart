package com.scalerkart.ecommerce.services.cart.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CartItemDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cartItemId;
    private Long cartId;
    private String productId;
    private Integer quantity;
    private Double price;

}