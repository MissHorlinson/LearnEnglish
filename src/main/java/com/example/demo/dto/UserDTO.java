package com.example.demo.dto;
import com.example.demo.model.*;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Level level;
    private List<Resource> resources;
    private Status status;
    private List<Role> roles;

    public UserDTO() { }

    public UserDTO(String name, String surname, String email, String password, Level level, Status status, List<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.level = level;
        this.status = status;
        this.roles = roles;
    }

    public UserDTO(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Resource> getLearnt() {
        return resources;
    }

    public void setLearnt(Resource learnt) {
        this.resources.add(learnt);
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
