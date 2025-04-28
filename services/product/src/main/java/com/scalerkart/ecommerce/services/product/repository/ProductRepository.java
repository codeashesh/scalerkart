package com.scalerkart.ecommerce.services.product.repository;

import com.scalerkart.ecommerce.services.product.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Full-text search on multiple fields
    @Query("{ '$or': [ " +
            "{ 'name': { $regex: ?0, $options: 'i' } }, " +
            "{ 'description': { $regex: ?0, $options: 'i' } }, " +
            "{ 'brand': { $regex: ?0, $options: 'i' } }, " +
            "{ 'material': { $regex: ?0, $options: 'i' } }, " +
            "{ 'color': { $regex: ?0, $options: 'i' } }, " +
            "{ 'tags': { $regex: ?0, $options: 'i' } } " +
            "{ 'category': { $regex: ?0, $options: 'i' } }, " +
            "{ 'categoryDetails.categoryTitle': { $regex: ?0, $options: 'i' } }, " +
            "{ 'categoryDetails.parentCategory.categoryTitle': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Product> searchProducts(String keyword);

    // Advanced filtering based on multiple parameters
    @Query("{ '$and': [ " +
            "{ $or: [ { 'categoryDetails.categoryTitle': ?0 }, { ?0: null } ] }, " +
            "{ $or: [ { 'brand': { $regex: ?1, $options: 'i' } }, { ?1: null } ] }, " +
            "{ $or: [ { 'material': { $regex: ?2, $options: 'i' } }, { ?2: null } ] }, " +
            "{ $or: [ { 'color': { $regex: ?3, $options: 'i' } }, { ?3: null } ] }, " +
            "{ 'price': { $gte: ?4, $lte: ?5 } }, " +
            "{ 'isActive': true }, " +
            "{ 'ratings.averageRating': { $gte: ?6 } } " +
            "] }")
    List<Product> filterProducts(String category, String brand, String material, String color, Double minPrice, Double maxPrice, Double minRating);

}
