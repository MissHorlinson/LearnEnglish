package com.example.demo.controller;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserInfoDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @Value("${jwt.token.secret}")
    private String secret;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User save(@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    @GetMapping("/get")
    public List<UserInfoDTO> getUsersCollection() {
        List<UserInfoDTO> users = new ArrayList<>() ;

        for (User user : userService.getUsersCollection()) {
            users.add(getUserInfo(user));
        }
        return users;
    }

    @GetMapping("/getId/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public User update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @PutMapping("/updateLearnt")
    public User updateLearnt(@RequestBody User user) {
        return userService.updateLearnt(user);
    }

    @PutMapping("updateLevel/{id}")
    public User updateLevel(@PathVariable(name = "id") Long id) {
        return userService.updateLevel(id);
    }

    private UserInfoDTO getUserInfo(User user) {
        return UserInfoDTO.userInfo(user);
    }

    public Long decode(String token) {
        token = token.substring(7);
        Claims body = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        Long id = Long.parseLong(body.get("id").toString());

        return id;
    }
}
