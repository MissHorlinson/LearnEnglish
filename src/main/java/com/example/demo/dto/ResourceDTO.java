package com.example.demo.dto;

public class ResourceDTO {

    private Long id;
    private String link;
    private String level;
    private String type;

    public ResourceDTO() { }

    public ResourceDTO(String link, String level, String type) {
        this.link = link;
        this.level = level;
        this.type = type;
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

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResourceDTO {" +
                "id = " + id +
                ", link = '" + link + "\'" +
                ", level = '" + level + "\'";
    }
}
