package com.scalerkart.ecommerce.services.product.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private Double averageRating;
    private Integer totalReviews;
}
