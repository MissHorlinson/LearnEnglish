package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "level")
    private String level;

    @Column(name = "type")
    private String type;

    public Resource() { }

    public Resource(String link, String level, String type) {
        this.link = link;
        this.level = level;
        this.type = type;
    }

    public Long getId() {
        return id;
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
}
