package com.example.demo.controller;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;
import com.example.demo.service.ResourceService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/get")
    public List<Resource> getResourceCollection() {
        return resourceService.getResourceCollection();
    }

    @GetMapping("/getId/{id}")
    public Resource getResourceById(@PathVariable(name = "id") Long id) {
        return resourceService.getResourceById(id);
    }

    @GetMapping("/getLevel/{level}")
    public Resource[] getResourceByLevel(@PathVariable(name = "level") Level level) {
        return resourceService.getResourceByLevel(level);
    }

    @GetMapping("/getType/{type}")
    public Resource[] getResourceByType(@PathVariable(name = "type") Type type) {
        return resourceService.getResourceByType(type);
    }

    @GetMapping("/start")
    public Resource getResourceByLevelAndType(Level level, Type type, List<Long> learnt) {
        return resourceService.start(level, type, learnt);
    }
}
