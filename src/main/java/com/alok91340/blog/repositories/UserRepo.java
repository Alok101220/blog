package com.alok91340.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

    Optional<User> findByEmail(String email);
    
}
