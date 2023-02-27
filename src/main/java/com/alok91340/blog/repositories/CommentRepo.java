package com.alok91340.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
    
}
