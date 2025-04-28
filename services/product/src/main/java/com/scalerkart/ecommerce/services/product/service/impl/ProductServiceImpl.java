package com.scalerkart.ecommerce.services.product.service.impl;

import com.scalerkart.ecommerce.services.product.exception.wrapper.ProductNotFoundException;
import com.scalerkart.ecommerce.services.product.model.convertor.ProductConvertor;
import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;
import com.scalerkart.ecommerce.services.product.model.entity.Category;
import com.scalerkart.ecommerce.services.product.model.entity.Product;
import com.scalerkart.ecommerce.services.product.repository.ProductRepository;
import com.scalerkart.ecommerce.services.product.service.CategoryService;
import com.scalerkart.ecommerce.services.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final MongoTemplate mongoTemplate;

    @Override
    public List<ProductDto> findAll() {
        log.info("ProductDto List, service, fetch all products");
        return productRepository.findAll()
                .stream()
                .map(ProductConvertor::toDto)
                .distinct()
                .toList();
    }

    @Override
    public List<ProductDto> findAllByCategory(String categoryId) {
        return findAll().stream()
                .filter(p -> Objects.equals(p.getCategoryDto().getCategoryId(), categoryId)
                        || (p.getCategoryDto().getParentCategory() != null && Objects.equals(p.getCategoryDto().getParentCategory().getCategoryId(), categoryId))
                        || (p.getCategoryDto().getParentCategory() != null && p.getCategoryDto().getParentCategory().getParentCategory() != null && Objects.equals(p.getCategoryDto().getParentCategory().getParentCategory().getCategoryId(), categoryId)))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(String productId) {
        log.info("ProductDto, service; fetch product by id");
        return productRepository.findById(productId).map(ProductConvertor::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + productId));
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream().map(ProductConvertor::toDto).toList();
    }

    @Override
    public List<ProductDto> filterProducts(String category, String brand, String material, String color, Double minPrice, Double maxPrice, Double minRating) {
        List<Criteria> criteriaList = new ArrayList<>();

        // Handle category filtering with subcategories
        if (category != null) {
            List<Category> matchingCategories = categoryService.findCategoryWithSubcategories(category);
            List<String> categoryIds = matchingCategories.stream()
                    .map(Category::getId)
                    .toList();
            criteriaList.add(Criteria.where("categoryDetail.id").in(categoryIds));
        }

        if (brand != null && !brand.isEmpty()) criteriaList.add(Criteria.where("brand").regex(brand, "i"));
        if (material != null && !material.isEmpty()) criteriaList.add(Criteria.where("material").regex(material, "i"));
        if (color != null && !color.isEmpty()) criteriaList.add(Criteria.where("color").regex(color, "i"));

        criteriaList.add(Criteria.where("price").gte(minPrice).lte(maxPrice));
        criteriaList.add(Criteria.where("isActive").is(true));
        criteriaList.add(Criteria.where("rating.averageRating").gte(minRating));

        // Combine all criteria with AND condition
        Criteria finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));

        Query query = new Query(finalCriteria);
        List<Product> filteredProducts = mongoTemplate.find(query, Product.class);

        return filteredProducts.stream().map(ProductConvertor::toDto).toList();
    }


    @Override
    public ProductDto save(ProductDto productDto) {
        log.info("ProductDto, service; save product");
        try {
            return ProductConvertor.toDto(productRepository.save(ProductConvertor.fromDto(productDto)));
        } catch (DataIntegrityViolationException e) {
            log.error("Error saving product: Data integrity violation", e);
            throw new ProductNotFoundException("Error saving product: Data integrity violation", e);
        } catch (Exception e) {
            log.error("Error saving product", e);
            throw new ProductNotFoundException("Error saving product", e);
        }
    }

    @Override
    public List<ProductDto> saveAll(final List<ProductDto> productListDto) {
        return productRepository
                .saveAll(productListDto
                        .stream()
                        .map(ProductConvertor::fromDto)
                        .toList())
                .stream()
                .map(ProductConvertor::toDto)
                .toList();
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        log.info("ProductDto, service; update product");
        productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productDto.getProductId()));
        return ProductConvertor.toDto(productRepository.save(ProductConvertor.fromDto(productDto)));
    }

    @Override
    public ProductDto update(String productId, ProductDto productDto) {
        log.info("ProductDto, service; update product with productId");
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        return ProductConvertor.toDto(productRepository.save(ProductConvertor.fromDto(productDto)));
    }

    @Override
    public void deleteById(String productId) {
        log.info("Void, service; delete product by id");
        productRepository.delete(ProductConvertor.fromDto(findById(productId)));
    }

}
