package com.example.demo.controller;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;
import com.example.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/resources")
public class AdminResourceController {
    ResourceService resourceService;

    @Autowired
    public AdminResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/save")
    public Resource save(@RequestBody ResourceDTO resourceDTO) {
        return resourceService.postResource(resourceDTO);
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

    @PutMapping("/update")
    public Resource update(@RequestBody ResourceDTO resourceDTO) {
        try {
            return resourceService.update(resourceDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        try {
            resourceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
