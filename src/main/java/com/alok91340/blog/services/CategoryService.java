package com.alok91340.blog.services;

import java.util.List;

import com.alok91340.blog.payloads.CategoryDto;

public interface CategoryService {
    
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getAllCategory();
    void deleteCategory(Integer categoryId);
}
