package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
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

    @GetMapping("/getId/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateRole/{id}")
    public User updateRole(@PathVariable(name = "id") Long id, @RequestBody Role role) {
        return userService.updateRole(id, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody UserDTO userDTO) {
        try {
            System.out.println("controller");
            userService.delete(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
