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
        return userService.save(userDTO);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public User update(@RequestBody UserDTO userDTO) {
        try {
            return userService.update(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
