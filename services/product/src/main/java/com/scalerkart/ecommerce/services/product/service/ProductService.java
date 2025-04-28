package com.scalerkart.ecommerce.services.product.service;

import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(final String productId);

    List<ProductDto> searchProducts(String keyword);

    List<ProductDto> filterProducts(String category, String brand, String material, String color, Double minPrice, Double maxPrice, Double minRating);
    ProductDto save(final ProductDto productDto);

    List<ProductDto> saveAll(final List<ProductDto> productListDto);

    ProductDto update(final ProductDto productDto);

    ProductDto update(final String productId, final ProductDto productDto);

    void deleteById(final String productId);

    List<ProductDto> findAllByCategory(String categoryId);
}
