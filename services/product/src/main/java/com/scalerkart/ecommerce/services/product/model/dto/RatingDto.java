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
public class RatingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Double averageRating;
    private Integer numberOfReviews;
}
