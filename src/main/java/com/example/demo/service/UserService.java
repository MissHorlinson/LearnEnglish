package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserUpdateException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), userDTO.getLevel());
        user = userRepository.save(user);
        log.info("User " + userDTO.toString() + " created");
        return user;
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
}
