package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserUpdateException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Logger         log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private RestTemplate   restTemplate;

    public UserService(UserRepository userRepository, RestTemplateBuilder restTemplateBuilder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public User postUser(UserDTO userDTO) {
        String url = "https://jsonplaceholder.typicode.com/posts";

        Map<String, Object> map = new HashMap<>();
        map.put("password", userDTO.getPassword());
        map.put("name", userDTO.getName());
        map.put("surname", userDTO.getSurname());
        map.put("level", userDTO.getLevel());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, buildHeaders());
        ResponseEntity<User> response = this.restTemplate.postForEntity(url, entity, User.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("User " + userDTO.toString() + " created");
            return response.getBody();
        } else {
            log.warn("Can not create " + userDTO.toString() + " user");
            return null;
        }
        /*User user = new User(userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), userDTO.getLevel());
        user = userRepository.save(user);
        log.info("User " + userDTO.toString() + " created");
        return user;*/
    }

    public User[] getUsersCollection() {
        String url = "https://api.monobank.ua/bank/currency";
        ResponseEntity<User[]> userCollection = this.restTemplate.getForEntity(url, User[].class);

        if (userCollection.getStatusCode() == HttpStatus.OK) {
            return userCollection.getBody();
        } else {
            log.warn("Can not take users collection");
            return null;
        }
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User update(UserDTO userDTO) throws Exception {
        if (userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.getOne(userDTO.getId());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setLevel(userDTO.getLevel());
            user = userRepository.save(user);
            log.info("User " + userDTO.toString() + " updated");
            return user;
        }
        log.warn("Can not update user - " + userDTO.toString());
        throw new UserUpdateException(
                "Can not update user - " + userDTO.toString());
    }

    public void delete(UserDTO userDTO) {
        if(userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.getOne(userDTO.getId());
            userRepository.delete(user);
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
