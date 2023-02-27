package com.alok91340.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alok91340.blog.entities.User;
import com.alok91340.blog.excepctions.ResourceNotFoundExcepction;
import com.alok91340.blog.repositories.UserRepo;

@Service
public class CustomUserDetailSevice implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=this.userRepo.findByEmail(username) .orElseThrow(()-> new ResourceNotFoundExcepction("user", "email: "+username, 0));
        return user;
    }
    
}
