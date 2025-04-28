package com.scalerkart.ecommerce.services.product.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dimension {
    private Double length;
    private Double width;
    private Double height;
}
