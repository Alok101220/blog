package com.alok91340.blog.services;

import java.util.List;

import com.alok91340.blog.payloads.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}
