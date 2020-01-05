package com.example.demo.service;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceUpdateException;
import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;
import com.example.demo.model.User;
import com.example.demo.repository.ResourceRepository;
import org.slf4j.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ResourceService {

    private Logger             log = LoggerFactory.getLogger(ResourceService.class);
    private RestTemplate       restTemplate;
    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository, RestTemplateBuilder restTemplateBuilder) {
        this.resourceRepository = resourceRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public Resource postResource(ResourceDTO resourceDTO) {
        Resource resource = new Resource(resourceDTO.getLink(), resourceDTO.getLevel(), resourceDTO.getType());
        resource = resourceRepository.save(resource);
        log.info("IN post - resource: resource " + resourceDTO.toString() + " created");
        return resource;
    }


    public List<Resource> getResourceCollection() {
         return resourceRepository.findAll();
    }

    public Resource getResourceById(Long id) {
        Resource resource = resourceRepository.findResourceById(id);
        if (resource == null) {
            throw new ResourceNotFoundException(
                    "Resource with id = " + id + " not found");
        }
        log.info("IN resourceById: resource found by id: " + resource);
        return resource;
    }

    public Resource[] getResourceByLevel(Level level) {
        Resource resource[] = resourceRepository.findAllByLevel(level);
        if (resource.length == 0) {
            throw new ResourceNotFoundException(
                    "Resource with level = " + level + " not found");
        }
        log.info("IN resourceByLevel: resource found by level: " + resource);
        return resource;
    }

    public Resource[] getResourceByType(Type type) {
        Resource resource[] = resourceRepository.findResourceByType(type);
        if (resource.length == 0) {
            throw new ResourceNotFoundException(
                    "Resource with type = " + type + " not found");
        }
        log.info("IN resourceByType: resource found by type: " + resource);
        return resource;
    }

    public Resource update(ResourceDTO resourceDTO) {
        if (resourceRepository.findById(resourceDTO.getId()).isPresent()) {
            Resource resource = resourceRepository.getOne(resourceDTO.getId());
            resource.setLevel(resourceDTO.getLevel());
            resource.setLink(resourceDTO.getLink());
            resource.setType(resourceDTO.getType());
            resource = resourceRepository.save(resource);
            log.info("IN resource - update: resource " + resourceDTO.toString() + " updated");
            return resource;
        }
        log.warn("IN resource - update: can not update resource - " + resourceDTO.toString());
        throw new ResourceUpdateException(
                "Can not update resource - " + resourceDTO.toString());
    }

    public void delete(Long id) {
        if (resourceRepository.findById(id).isPresent()) {
            Resource resource = resourceRepository.getOne(id);
            for (User u: resource.getUsers()) {
                u.getLearnt().remove(resource);
            }
            resourceRepository.save(resource);
            resourceRepository.delete(resource);
            if (resourceRepository.findById(id).isPresent()) {
                log.warn("IN resource - delete: something wrong with deleting resource");
            } else {
                log.info("IN resource - delete: resource deleted successful");
            }
        }
    }


    public Resource start(Level level, Type type, List<Long> learnt) {
        if (checkLevel(learnt, level)) {
            List<Resource> resources;
            if (learnt.isEmpty()) {
                resources = resourceRepository.findByLevelAndType(level, type);
            } else {
                resources = resourceRepository.findByLevelAndType(level, type, learnt);
            }
            if (resources == null) {
                log.warn("IN start: can not find resource with level: " + level + " and type: " + type);
                throw new ResourceNotFoundException(
                        "Resource with level = " + level + " and type: " + type + " not found");
            }
            return resources.get(resources.size() - 1);
        }
        log.info("IN resource by level and type: all resources was learnt");
        return null;
    }

    public boolean checkLevel(List<Long> learnt, Level level) {
        System.out.println(learnt.size());
        System.out.println(resourceRepository.countByLevel(level));
        return learnt.size() < resourceRepository.countByLevel(level);
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
