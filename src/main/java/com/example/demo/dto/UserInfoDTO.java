package com.example.demo.dto;

import com.example.demo.model.Level;
import com.example.demo.model.User;

public class UserInfoDTO {

    private String name;
    private String surname;
    private Level level;

    public UserInfoDTO() { }

    public static UserInfoDTO userInfo(User user) {
       UserInfoDTO infoDTO = new UserInfoDTO();
       infoDTO.setName(user.getName());
       infoDTO.setSurname(user.getSurname());
       infoDTO.setLevel(user.getLevel());
       return infoDTO;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
