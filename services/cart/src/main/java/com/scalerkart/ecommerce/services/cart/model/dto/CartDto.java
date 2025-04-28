package com.scalerkart.ecommerce.services.cart.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CartDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private String status;

    @JsonInclude(Include.NON_NULL)
    private List<CartItemDto> items;

}