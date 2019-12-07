package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User save(@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get")
    public User[] getUsersCollection() {
        return userService.getUsersCollection();
    }

    @PutMapping("/update")
    public User update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody UserDTO userDTO) {
        try {
            userService.delete(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
