package com.example.demo.dto;

public class UserDTO {

    private Long id;
    private String password;
    private String name;
    private String surname;
    private String level;
    private int status;
    // private Set<Role> roles;

    public UserDTO(String password, String name, String surname, String level /*, int status, Set<Role> roles*/) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.level = level;
        //this.status = status;
        // this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
/*
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

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
