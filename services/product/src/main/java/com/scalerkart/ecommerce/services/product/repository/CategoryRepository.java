package com.scalerkart.ecommerce.services.product.repository;

import com.scalerkart.ecommerce.services.product.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByCategoryTitle(String title);
}
