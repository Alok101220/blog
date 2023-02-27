package com.alok91340.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alok91340.blog.payloads.ApiResponse;
import com.alok91340.blog.payloads.CategoryDto;
import com.alok91340.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto2, HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
            @PathVariable Integer categoryId) {
        CategoryDto categoryDto2 = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(categoryDto2);
    }

    // deleteCategory
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        String message = this.categoryService.getCategoryById(categoryId).getCategoryTitle() + " deleted..";
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, true), HttpStatus.OK);
    }

    // get-category-by-id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    // get-all-categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categories = this.categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
