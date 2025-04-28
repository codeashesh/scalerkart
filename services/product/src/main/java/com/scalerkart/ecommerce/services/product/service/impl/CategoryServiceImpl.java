package com.scalerkart.ecommerce.services.product.service.impl;

import com.scalerkart.ecommerce.services.product.exception.wrapper.CategoryNotFoundException;
import com.scalerkart.ecommerce.services.product.model.convertor.CategoryConvertor;
import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.model.entity.Category;
import com.scalerkart.ecommerce.services.product.repository.CategoryRepository;
import com.scalerkart.ecommerce.services.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final MongoTemplate mongoTemplate;

    @Override
    public List<CategoryDto> findAll() {
        log.info("Category List Service, fetch all category");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryConvertor::toDto)
                .toList();
    }

    @Override
    public List<Category> findCategoryWithSubcategories(String categoryTitle) {
        Category rootCategory = mongoTemplate.findOne(
                Query.query(Criteria.where("categoryTitle").is(categoryTitle)), Category.class);
        if (rootCategory == null) {
            return Collections.emptyList();
        }
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(Criteria.where("parentCategory.id").is(rootCategory.getId())));
//        List<Category> subcategories = mongoTemplate.aggregate(aggregation, "categories", Category.class).getMappedResults();
//        List<Category> allCategories = new ArrayList<>();
//        allCategories.add(rootCategory);
//        allCategories.addAll(subcategories);
//        return allCategories;
        List<Category> allCategories = new ArrayList<>();
        allCategories.add(rootCategory);
        fetchSubcategories(rootCategory.getId(), allCategories);
        return allCategories;
    }

    private void fetchSubcategories(String parentId, List<Category> allCategories) {
        List<Category> subcategories = mongoTemplate.find(
                Query.query(Criteria.where("parentCategory.id").is(parentId)),
                Category.class
        );
        if (!subcategories.isEmpty()) {
            allCategories.addAll(subcategories);
            for (Category subcategory : subcategories) {
                fetchSubcategories(subcategory.getId(), allCategories); // Recursive call
            }
        }
    }

    @Override
    public Page<CategoryDto> findAllCategory(int page, int size) {
        log.info("CategoryDto List, service; fetch all categories");
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryDto> categoryDtos = categoryPage.getContent()
                .stream()
                .map(CategoryConvertor::toDto)
                .distinct()
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtos, pageable, categoryPage.getTotalElements());
    }

    @Override
    public List<CategoryDto> getAllCategories(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Category> pagedResult = categoryRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent()
                    .stream()
                    .map(CategoryConvertor::toDto)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public CategoryDto findById(String categoryId) {
        log.info("CategoryDto Service, fetch category by id");
        return categoryRepository.findById(categoryId)
                .map(CategoryConvertor::toDto)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id[%d] not found", categoryId)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        log.info("CategoryDto, service; save category");
        return CategoryConvertor.toDto(categoryRepository.save(CategoryConvertor.fromDto(categoryDto)));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("CategoryDto Service, update category");
        return CategoryConvertor.toDto(categoryRepository.save(CategoryConvertor.fromDto(categoryDto)));
    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        log.info("CategoryDto Service: Updating category with categoryId");
        try {
            final Category existingCategory = CategoryConvertor.fromDto(findById(categoryId));
            BeanUtils.copyProperties(categoryDto, existingCategory, "categoryId", "parentCategoryDto");
            if (categoryDto.getParentCategory() != null) {
                existingCategory.setParentCategory(CategoryConvertor.fromDto(categoryDto.getParentCategory()));
            }
            return CategoryConvertor.toDto(categoryRepository.save(existingCategory));
        } catch (CategoryNotFoundException e) {
            log.error("Error updating category. Category with id [{}] not found.", categoryId);
            throw new CategoryNotFoundException(String.format("Category with id [%d] not found.", categoryId), e);
        } catch (DataIntegrityViolationException e) {
            log.error("Error updating category: Data integrity violation", e);
            throw new CategoryNotFoundException("Error updating category: Data integrity violation", e);
        } catch (Exception e) {
            log.error("Error updating category", e);
            throw new CategoryNotFoundException("Error updating category", e);
        }
    }

    @Override
    public void deleteById(String categoryId) {
        log.info("Void Service, delete category by id");
        try {
            categoryRepository.deleteById(categoryId);
        } catch (CategoryNotFoundException e) {
            log.error("Error delete category", e);
            throw new CategoryNotFoundException("Error updating category", e);
        }
    }
}
