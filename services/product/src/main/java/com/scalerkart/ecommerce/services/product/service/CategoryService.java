package com.scalerkart.ecommerce.services.product.service;

import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.model.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    List<Category> findCategoryWithSubcategories(String categoryTitle);

    Page<CategoryDto> findAllCategory(int page, int size);
    List<CategoryDto> getAllCategories(Integer pageNo, Integer pageSize, String sortBy);
    CategoryDto findById(final String categoryId);
    CategoryDto save(final CategoryDto categoryDto);
    CategoryDto update(final CategoryDto categoryDto);
    CategoryDto update(final String categoryId, final CategoryDto categoryDto);
    void deleteById(final String categoryId);

}
