package com.example.demo.service;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.exception.ResourceUpdateException;
import com.example.demo.model.Resource;
import com.example.demo.repository.ResourceRepository;
import org.slf4j.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResourceService {

    private Logger log = LoggerFactory.getLogger(ResourceService.class);
    private RestTemplate restTemplate;

    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository, RestTemplateBuilder restTemplateBuilder) {
        this.resourceRepository = resourceRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public Resource postResource(ResourceDTO resourceDTO) {
        String url = "https://jsonplaceholder.typicode.com/posts";

        Map<String, Object> map = new HashMap<>();
        map.put("link", resourceDTO.getLink());
        map.put("level", resourceDTO.getLevel());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, buildHeaders());
        ResponseEntity<Resource> response = this.restTemplate.postForEntity(url, entity, Resource.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("Resourse " + resourceDTO.toString() + " created");
            return response.getBody();
        } else {
            log.warn("Can not create " + resourceDTO.toString() + " resource");
            return null;
        }
        /*Resource resource = new Resource(resourceDTO.getLink(), resourceDTO.getLevel());
        return resourceRepository.save(resource);*/
    }


    public Resource[] getResourceCollection() {
        String url = "https://api.monobank.ua/bank/currency";
        ResponseEntity<Resource[]> resourceCollection = this.restTemplate.getForEntity(url, Resource[].class);

        return resourceCollection.getBody();
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
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
