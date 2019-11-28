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

    public Resource() { }

    public Resource(String link, String level) {
        this.link = link;
        this.level = level;
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
}
