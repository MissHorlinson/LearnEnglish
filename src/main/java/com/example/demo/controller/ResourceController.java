package com.example.demo.controller;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.model.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/save")
    public Resource save(@RequestBody ResourceDTO resourceDTO) {
        return resourceService.postResource(resourceDTO);
    }

    @GetMapping("/get")
    public Resource[] getResourceCollection() {
        return resourceService.getResourceCollection();
    }

    @GetMapping("/getId/{id}")
    public Resource getResourceById(@PathVariable(name = "id") Long id) {
        return resourceService.getResourceById(id);
    }

    @GetMapping("/getLevel/{level}")
    public Resource getResourceByLevel(@PathVariable(name = "level") String level) {
        return resourceService.getResourceByLevel(level);
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

    @DeleteMapping("/delete")
    public void delete(@RequestBody Long id) {
        try {
            resourceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
