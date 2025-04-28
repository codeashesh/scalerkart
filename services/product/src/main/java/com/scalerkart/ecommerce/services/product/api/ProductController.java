package com.scalerkart.ecommerce.services.product.api;

import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;
import com.scalerkart.ecommerce.services.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAll() {
        log.info("ProductDto List, controller; fetch all categories");
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String color,
            @RequestParam(required = false, defaultValue = "0") Double minPrice,
            @RequestParam(required = false, defaultValue = "10000000") Double maxPrice,
            @RequestParam(required = false, defaultValue = "0") Double minRating) {
        return ResponseEntity.ok(productService.filterProducts(category, brand, material, color, minPrice, maxPrice, minRating));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable("productId")
                                               @NotBlank(message = "Input must not be blank!")
                                               @Valid final String productId) {
        log.info("ProductDto, resource; fetch product by id");
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody
                                           @NotNull(message = "Input must not be NULL!")
                                           @Valid final ProductDto productDto) {
        log.info("ProductDto, resource; save product");
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<ProductDto>> saveAll(@RequestBody final List<ProductDto> productListDto) {
        log.info("ProductDto, resource; save product");
        return ResponseEntity.ok(productService.saveAll(productListDto));
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody
                                             @NotNull(message = "Input must not be NULL!")
                                             @Valid final ProductDto productDto) {
        log.info("ProductDto, resource; update product");
        return ResponseEntity.ok(productService.update(productDto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@PathVariable("productId")
                                             @NotBlank(message = "Input must not be blank!")
                                             @Valid final String productId,
                                             @RequestBody
                                             @NotNull(message = "Input must not be NULL!")
                                             @Valid final ProductDto productDto) {
        log.info("ProductDto, resource; update product with productId");
        return ResponseEntity.ok(productService.update(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("productId") final String productId) {
        log.info("Boolean, resource; delete product by id");
        productService.deleteById(productId);
        return ResponseEntity.ok(true);
    }

}
