package com.scalerkart.ecommerce.services.product.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    private String id;
    private String categoryTitle;
    private String imageUrl;
    private Set<Category> subCategories;
    private Category parentCategory;

}
