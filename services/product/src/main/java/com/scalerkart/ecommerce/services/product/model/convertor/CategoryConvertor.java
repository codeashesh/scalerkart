package com.scalerkart.ecommerce.services.product.model.convertor;

import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.model.entity.Category;

import java.util.stream.Collectors;

public interface CategoryConvertor {

    static Category fromDto(CategoryDto dto) {
        return Category.builder()
                .id(dto.getCategoryId())
                .categoryTitle(dto.getCategoryTitle())
                .imageUrl(dto.getImageUrl())
                .subCategories(dto.getSubCategories() != null ?
                        dto.getSubCategories().stream().map(CategoryConvertor::fromDto).collect(Collectors.toSet())
                        : null)
                .parentCategory(dto.getParentCategory() != null ? fromDto(dto.getParentCategory()) : null)
                .build();
    }

    static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getId())
                .categoryTitle(category.getCategoryTitle())
                .imageUrl(category.getImageUrl())
                .subCategories(category.getSubCategories() != null ?
                        category.getSubCategories().stream().map(CategoryConvertor::toDto).collect(Collectors.toSet())
                        : null)
                .parentCategory(category.getParentCategory() != null ? toDto(category.getParentCategory()) : null)
                .build();
    }
}

