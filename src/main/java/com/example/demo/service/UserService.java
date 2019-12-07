package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UserUpdateException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserService {

    private Logger         log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RestTemplate   restTemplate;

    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, RestTemplateBuilder restTemplateBuilder/*, BCryptPasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.restTemplate = restTemplateBuilder.build();

       // this.passwordEncoder = passwordEncoder;
    }

    public User postUser(UserDTO userDTO) {
        Role role = roleRepository.findByRole("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getLevel(), userRoles);
        user = userRepository.save(user);
        log.info("User " + userDTO.toString() + " created");
        return user;
    }

    public User[] getUsersCollection() {
        String url = "http://localhost:8080/api/users";
        ResponseEntity<User[]> userCollection = this.restTemplate.getForEntity(url, User[].class);
        if (userCollection.getStatusCode() == HttpStatus.OK) {
            return userCollection.getBody();
        } else {
            log.warn("Can not take users collection");
            return null;
        }
    }

    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            log.warn("No user with id = " + id);
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        log.info("User found by id: " + user);
        return user;
    }

    public User getUserByName(String username) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            log.warn("No user with name = " + username);
            throw new UserUpdateException("User with username: " + username + " not found");
        }
        log.info("User found by name: " + user);
        return user;
    }

    public User update(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.getOne(userDTO.getId());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
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
            if (getUserById(userDTO.getId()) == null) {
                log.info("User " + userDTO.toString() + " deleted successful");
            } else {
                log.warn("Something wrong with deleting user " + userDTO.toString());
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
