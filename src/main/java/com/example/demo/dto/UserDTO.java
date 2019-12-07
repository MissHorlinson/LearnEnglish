package com.example.demo.dto;

import com.example.demo.model.Role;

import java.util.Set;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String level;
    private int status;
    private Set<Role> roles;

    public UserDTO() { }

    public UserDTO(String name, String surname, String email, String password,  String level /*, int status*/, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.level = level;
        //this.status = status;
        this.roles = roles;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
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
