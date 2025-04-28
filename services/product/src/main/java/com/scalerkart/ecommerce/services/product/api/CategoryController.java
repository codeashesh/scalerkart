package com.scalerkart.ecommerce.services.product.api;

import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("CategoryDto List, controller; fetch all categories");
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDto>> getAllPagedCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CategoryDto> categoryPage = categoryService.findAllCategory(page, size);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/page-sort")
    public ResponseEntity<List<CategoryDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "categoryId") String sortBy) {
        List<CategoryDto> list = categoryService.getAllCategories(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("categoryId")
                                                @NotBlank(message = "Input must not be blank")
                                                @Valid final String categoryId) {
        log.info("CategoryDto, resource; fetch category by id");
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @NotNull(message = "Input must not be NULL")
                                            @Valid final CategoryDto categoryDto) {
        log.info("CategoryDto, resource; save category");
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody
                                              @NotNull(message = "Input must not be NULL")
                                              @Valid final CategoryDto categoryDto) {
        log.info("CategoryDto, resource; update category");
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable("categoryId")
                                              @NotBlank(message = "Input must not be blank")
                                              @Valid final String categoryId,
                                              @RequestBody @NotNull(message = "Input must not be NULL")
                                              @Valid final CategoryDto categoryDto) {
        log.info("CategoryDto, resource; update category with categoryId");
        return ResponseEntity.ok(categoryService.update(categoryId, categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId) {
        log.info("Boolean, resource; delete category by id");
        categoryService.deleteById(categoryId);
        return ResponseEntity.ok(true);
    }

}
