package com.alok91340.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.blog.entities.Category;

public interface CategoryRepo  extends JpaRepository<Category,Integer>{
    
}
