package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class UserService {

    private Logger         log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RestTemplate   restTemplate;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, RestTemplateBuilder restTemplateBuilder, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.passwordEncoder = passwordEncoder;
    }

    public User postUser(UserDTO userDTO) {
        Role role = roleRepository.findByRole("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);

        User user = new User(userDTO.getName(),
                             userDTO.getSurname(),
                             userDTO.getEmail(),
                             encode(userDTO.getPassword()),
                             userDTO.getLevel(),
                             Status.ACTIVE,
                             userRoles);
        user = userRepository.save(user);
        log.info("IN postUser: " + userDTO.toString() + " created " );
        return user;
    }

    public List<User> getUsersCollection() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            log.warn("IN userById: no user with id - " + id);
            throw new UserNotFoundException("User with id - " + id + " not found");
        }
        log.info("IN userById: user found by id: " + user);
        return user;
    }

    public User getUserByName(String username) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            log.warn("IN userByName: no user with name - " + username);
            throw new UserUpdateException("User with username - " + username + " not found");
        }
        log.info("IN userByName: user found by name: " + user);
        return user;
    }

    public User update(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.getOne(userDTO.getId());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(encode(userDTO.getPassword()));
            user.setLevel(userDTO.getLevel());
            user = userRepository.save(user);
            log.info("IN user - update: user " + userDTO.toString() + " updated");
            return user;
        }
        log.warn("IN user - update: can not update user - " + userDTO.toString());
        throw new UserUpdateException(
                "Can not update user - " + userDTO.toString());
    }

    public User updateRole(Long id, Role role) {
        if (userRepository.findById(id).isPresent()) {
            role = roleRepository.findByRole(role.getRole());
            User user = userRepository.getOne(id);
            user.setRoles(role);
            user = userRepository.save(user);
            log.info("IN userRole - update: new Role added successfully");
            return user;
        }
        log.warn("In userRole - update: can not update user Role");
        throw new UserUpdateException(
                "Can not update user Role");
    }

    public User updateLearnt(User user) {
        user = userRepository.save(user);
        return user;
    }

    public User updateLevel(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.getOne(id);
            int level = user.getLevel().ordinal();
            user.setLevel(Level.values()[level + 1]);
            user = userRepository.save(user);
            log.info("IN userLevel - update: level updated");
            return user;
        }
        log.warn("IN userLevel - update: can not update level");
        throw new UserUpdateException(
                "Can not update user level");
    }

    public void delete(UserDTO userDTO) {
        if(userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.getOne(userDTO.getId());
            user.getRoles().clear();
            user.getLearnt().clear();
            userRepository.save(user);
            userRepository.delete(user);
            if (userRepository.findById(userDTO.getId()).isPresent()) {
                log.warn("IN user - delete: something wrong with deleting user " + userDTO.toString());
                throw new UserDeleteException("Can not delete user - " + userDTO.toString());
            } else {
                log.info("IN user - delete: user " + userDTO.toString() + " deleted successful");
            }
        }
    }

    public String encode(String password) {
        password = passwordEncoder.encode(password);
        return password;
    }
}
