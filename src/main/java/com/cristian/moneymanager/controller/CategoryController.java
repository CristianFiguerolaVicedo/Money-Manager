package com.cristian.moneymanager.controller;

import com.cristian.moneymanager.dto.CategoryDto;
import com.cristian.moneymanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCategory);
    }
}
