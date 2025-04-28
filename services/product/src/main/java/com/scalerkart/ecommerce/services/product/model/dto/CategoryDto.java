package com.scalerkart.ecommerce.services.product.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String categoryId;
    private String categoryTitle;
    private String imageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDto parentCategory;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CategoryDto> subCategories;


}
