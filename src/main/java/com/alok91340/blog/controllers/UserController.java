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
import com.alok91340.blog.payloads.UserDto;
import com.alok91340.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // post(create- user)
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    // update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    // delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {

        String message = this.userService.getUserById(userId).getName() + " deleted successfully..";
        userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, true), HttpStatus.OK);
    }

    // get-user-byId
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // get-all-users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userList = this.userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
