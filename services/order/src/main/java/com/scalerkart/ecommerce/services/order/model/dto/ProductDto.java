package com.scalerkart.ecommerce.services.order.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ProductDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productId;
    private String name;
    private String description;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private String imageUrl;
    private String brand;
    private Double weight;
    private String material;
    private String color;
    private Set<Integer> sizes;
    private Set<String> tags;

    private String productTitle;
    private String sku;
    private Double priceUnit;
    private Integer quantity;
    private Boolean isActive;
    private String category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDto categoryDto;

}
