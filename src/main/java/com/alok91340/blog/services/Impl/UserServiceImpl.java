package com.alok91340.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alok91340.blog.entities.User;
import com.alok91340.blog.excepctions.ResourceNotFoundExcepction;
import com.alok91340.blog.payloads.UserDto;
import com.alok91340.blog.repositories.UserRepo;
import com.alok91340.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExcepction("User", "id", userId));

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExcepction("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExcepction("User", "id", userId));
        this.userRepo.delete(user);

    }

    public User dtoToUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto, User.class);

        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId()) ;
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getPassword());
        return userDto;
    }

}
