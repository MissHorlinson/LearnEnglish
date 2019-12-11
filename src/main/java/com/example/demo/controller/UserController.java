package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User save(@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    @GetMapping("/get")
    public List<User> getUsersCollection() {
        return userService.getUsersCollection();
    }

    @PutMapping("/update")
    public User update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    private UserDTO getUserInfo(User user) {
        return UserDTO.forUser(user);
    }
}
