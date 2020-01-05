package com.example.demo.dto;

import com.example.demo.model.Level;
import com.example.demo.model.Resource;
import com.example.demo.model.Type;

public class ResourceDTO {

    private Long id;
    private String link;
    private Level level;
    private Type type;

    public ResourceDTO() { }

    public ResourceDTO(String link, Level level, Type type) {
        this.link = link;
        this.level = level;
        this.type = type;
    }

    public static String forUser(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setLink(resource.getLink());

        return resourceDTO.getLink();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResourceDTO {" +
                "id = " + id +
                ", link = '" + link + "\'" +
                ", level = '" + level + "\'" +
                ", type = '" + type + "\'";
    }
}
