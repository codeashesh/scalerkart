package com.scalerkart.ecommerce.services.product.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class DimensionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Double length;
    private Double width;
    private Double height;
    private String unit;
}
