package com.scalerkart.ecommerce.services.product.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private String id;

    private String name;
    private String description;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private String imageUrl;
    private String brand;
    private Double weight;
    private Dimension dimension;
    private String material;
    private String color;
    private Set<Integer> sizes;
    private Set<String> tags;
    private Rating rating;

    private String productTitle;
    private String sku;
    private Double priceUnit;
    private Integer quantity;
    private Boolean isActive;
    private String category;
    private Category categoryDetail;
}
