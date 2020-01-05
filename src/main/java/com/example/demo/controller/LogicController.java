package com.example.demo.controller;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LogicController {

    private ResourceController resourceController;
    private UserController     userController;

    public LogicController(ResourceController resourceController, UserController userController) {
        this.resourceController = resourceController;
        this.userController = userController;
    }

    @GetMapping("/start/{type}")
    public String start(@RequestHeader("Authorization") String token, @PathVariable String type) {
        User user = getUser(token);
        Level level = user.getLevel();
        Resource resource = resourceController.getResourceByLevelAndType(level, Type.valueOf(type.toUpperCase()), getLearnt(user));

        if (resource == null) {
           userController.updateLevel(user.getId());
        } else {
            user.setLearnt(resource);
            userController.updateLearnt(user);
        }
        return ResourceDTO.forUser(resource);
    }

    @GetMapping("/skip")
    public String  skip(@RequestHeader("Authorization") String token) {
        User user = getUser(token);
        Type type = user.getLearnt().get(user.getLearnt().size() - 1).getType();
        user.getLearnt().remove(user.getLearnt().size() - 1);
        userController.updateLearnt(user);
        return start(token, type.toString());
    }

    @GetMapping("/repeat")
    public List<Resource> repeat(@RequestHeader("Authorization") String token) {
        return getUser(token).getLearnt();
    }

    public User getUser(String token) {
        Long id = userController.decode(token);
        return userController.getUserById(id);
    }

    public List<Long> getLearnt(User user) {
        List<Long> learnt = new ArrayList<>();
        for (Resource resource:user.getLearnt()) {
            learnt.add(resource.getId());
        }
        return learnt;
    }
}
