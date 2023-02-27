package com.alok91340.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alok91340.blog.entities.Category;
import com.alok91340.blog.excepctions.ResourceNotFoundExcepction;
import com.alok91340.blog.payloads.CategoryDto;
import com.alok91340.blog.repositories.CategoryRepo;
import com.alok91340.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);

        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundExcepction("Category", "category id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryDescription());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundExcepction("Category", "category id", categoryId));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.categoryToDto(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundExcepction("Category", "category id", categoryId));
        this.categoryRepo.delete(category);

    }

    public Category dtoToCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }

    public CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

}
