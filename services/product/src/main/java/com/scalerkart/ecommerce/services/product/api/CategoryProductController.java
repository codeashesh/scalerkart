package com.scalerkart.ecommerce.services.product.api;

import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;
import com.scalerkart.ecommerce.services.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category/{categoryId}/product")
public class CategoryProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable("categoryId")
                                                    @NotBlank(message = "Input must not be blank")
                                                    @Valid final String categoryId) {
        log.info("ProductDto List, controller; fetch all categories");
        return ResponseEntity.ok(productService.findAllByCategory(categoryId));
    }

}
