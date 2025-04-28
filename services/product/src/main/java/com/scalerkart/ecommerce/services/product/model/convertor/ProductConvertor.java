package com.scalerkart.ecommerce.services.product.model.convertor;

import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.model.dto.DimensionDto;
import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;
import com.scalerkart.ecommerce.services.product.model.dto.RatingDto;
import com.scalerkart.ecommerce.services.product.model.entity.Category;
import com.scalerkart.ecommerce.services.product.model.entity.Dimension;
import com.scalerkart.ecommerce.services.product.model.entity.Product;
import com.scalerkart.ecommerce.services.product.model.entity.Rating;

public interface ProductConvertor {

    static Product fromDto(ProductDto dto) {
        return Product.builder()
                .id(dto.getProductId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .discountedPrice(dto.getDiscountedPrice())
                .stock(dto.getStock())
                .imageUrl(dto.getImageUrl())
                .brand(dto.getBrand())
                .weight(dto.getWeight())
                .dimension(dto.getDimension() != null ? fromDimensionDto(dto.getDimension()) : null)
                .material(dto.getMaterial())
                .color(dto.getColor())
                .sizes(dto.getSizes())
                .tags(dto.getTags())
                .rating(dto.getRatings() != null ? fromRatingDto(dto.getRatings()) : null)
                .productTitle(dto.getProductTitle())
                .sku(dto.getSku())
                .priceUnit(dto.getPriceUnit())
                .quantity(dto.getQuantity())
                .isActive(dto.getIsActive())
                .category(dto.getCategory())
                .categoryDetail(dto.getCategoryDto() != null ? fromCategoryDto(dto.getCategoryDto()) : null)
                .build();
    }

    static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountedPrice(product.getDiscountedPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .brand(product.getBrand())
                .weight(product.getWeight())
                .dimension(product.getDimension() != null ? toDimensionDto(product.getDimension()) : null)
                .material(product.getMaterial())
                .color(product.getColor())
                .sizes(product.getSizes())
                .tags(product.getTags())
                .ratings(product.getRating() != null ? toRatingDto(product.getRating()) : null)
                .productTitle(product.getProductTitle())
                .sku(product.getSku())
                .priceUnit(product.getPriceUnit())
                .quantity(product.getQuantity())
                .isActive(product.getIsActive())
                .category(product.getCategory())
                .categoryDto(product.getCategoryDetail() != null ? toCategoryDto(product.getCategoryDetail()) : null)
                .build();
    }

    private static Dimension fromDimensionDto(DimensionDto dto) {
        return Dimension.builder()
                .length(dto.getLength())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .build();
    }

    private static DimensionDto toDimensionDto(Dimension dimension) {
        return DimensionDto.builder()
                .length(dimension.getLength())
                .width(dimension.getWidth())
                .height(dimension.getHeight())
                .build();
    }

    private static Rating fromRatingDto(RatingDto dto) {
        return Rating.builder()
                .averageRating(dto.getAverageRating())
                .totalReviews(dto.getNumberOfReviews())
                .build();
    }

    private static RatingDto toRatingDto(Rating rating) {
        return RatingDto.builder()
                .averageRating(rating.getAverageRating())
                .numberOfReviews(rating.getTotalReviews())
                .build();
    }

    private static Category fromCategoryDto(CategoryDto dto) {
        return CategoryConvertor.fromDto(dto);
    }

    private static CategoryDto toCategoryDto(Category category) {
        return CategoryConvertor.toDto(category);
    }
}

