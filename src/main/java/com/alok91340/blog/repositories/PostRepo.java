package com.alok91340.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.blog.entities.Category;
import com.alok91340.blog.entities.Post;
import com.alok91340.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
   
    
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
    
    
}
