package com.example.demo.service;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceUpdateException;
import com.example.demo.model.Resource;
import com.example.demo.repository.ResourceRepository;
import org.slf4j.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;


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
        Resource resource = new Resource(resourceDTO.getLink(), resourceDTO.getLevel());
        resource = resourceRepository.save(resource);
        log.info("Resource " + resourceDTO.toString() + " created");
        return resource;
    }


    public Resource[] getResourceCollection() {
        String url = "http://localhost:8080/api/users";
        ResponseEntity<Resource[]> resourceCollection = this.restTemplate.getForEntity(url, Resource[].class);

        return resourceCollection.getBody();
    }

    public Resource getResourceById(Long id) {
        Resource resource = resourceRepository.findResourceById(id);
        if (resource == null) {
            throw new ResourceNotFoundException(
                    "Resource with id = " + id + " not found");
        }
        log.info("Resource found by id: " + resource);
        return resource;
    }

    public Resource getResourceByLevel(String level) {
        Resource resource = resourceRepository.findResourceByLevel(level);
        if (resource == null) {
            throw new ResourceNotFoundException(
                    "Resource with level = " + level + " not found");
        }
        log.info("Resource found by level: " + resource);
        return resource;
    }

    public Resource update(ResourceDTO resourceDTO) {
        if (resourceRepository.findById(resourceDTO.getId()).isPresent()) {
            Resource resource = resourceRepository.getOne(resourceDTO.getId());
            resource.setLevel(resourceDTO.getLevel());
            resource.setLink(resourceDTO.getLink());
            resource = resourceRepository.save(resource);
            log.info("Resource " + resourceDTO.toString() + " updated");
            return resource;
        }
        log.warn("Can not update resource - " + resourceDTO.toString());
        throw new ResourceUpdateException(
                "Can not update resource - " + resourceDTO.toString());
    }

    public void delete(Long id) {
        if (resourceRepository.findById(id).isPresent()) {
            Resource resource = resourceRepository.getOne(id);
            resourceRepository.delete(resource);
            if (getResourceById(id) == null) {
                log.info("Resource deleted successful");
            } else {
                log.warn("Something wrong with deleting resource");
            }
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
