package com.example.demo.service;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.exception.ResourceUpdateException;
import com.example.demo.model.Resource;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource save(ResourceDTO resourceDTO) {
        Resource resource = new Resource(resourceDTO.getLink(), resourceDTO.getLevel());
        return resourceRepository.save(resource);
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findResourceById(id);
    }

    public Resource getResourceByLevel(String level) {
        return resourceRepository.findResourceByLevel(level);
    }

    public Resource update(ResourceDTO resourceDTO) throws Exception{
        if (resourceRepository.findById(resourceDTO.getId()).isPresent()) {
            Resource resource = resourceRepository.getOne(resourceDTO.getId());
            resource.setLevel(resourceDTO.getLevel());
            resource.setLink(resourceDTO.getLink());
            return resourceRepository.save(resource);
        }
        throw new ResourceUpdateException(
                "Can not update resource - " + resourceDTO.toString());
    }

    public void delete(Long id) {
        if (resourceRepository.findById(id).isPresent()) {
            Resource resource = resourceRepository.getOne(id);
            resourceRepository.delete(resource);
        }
    }
}
