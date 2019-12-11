package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/save")
    public User save(@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    @GetMapping("/users/get")
    public List<User> getUsersCollection() {
        return userService.getUsersCollection();
    }

    @GetMapping("/users/getId/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/delete")
    public void delete(@RequestBody UserDTO userDTO) {
        try {
            userService.delete(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
