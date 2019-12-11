package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.User;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String level;
    private Status status;
    private List<Role> roles;

    public UserDTO() { }

    public UserDTO(String name, String surname, String email, String password, String level , Status status, List<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.level = level;
        this.status = status;
        this.roles = roles;
    }


    public static UserDTO forUser(User user) {
        UserDTO userDTO = new UserDTO();
        //userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setLevel(user.getLevel());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO {" +
                "id = " + id +
                ", password = '" + password + "\'" +
                ", name = '" + name + "\'" +
                ", surname = '" + surname + "\'" +
                ", level = '" + level + "\'" +
                "}";
    }
}
