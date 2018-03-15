package com.goapptiv.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMAILTEMPLATES")
public class Template implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    /**
     * Default Constructor
     */
    public Template() {}

    /**
     * Templates Constructor
     *
     * @param name (required) name of template
     * @param location (required) template location
     */
    public Template(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
