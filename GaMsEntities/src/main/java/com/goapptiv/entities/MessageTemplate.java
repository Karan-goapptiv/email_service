package com.goapptiv.entities;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGETEMPLATES")
public class MessageTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String templates;

    /**
     * Default Constructor
     */
    public MessageTemplate() {}

    /**
     * MessageTemplate Constructor
     *
     * @param name (required) name of template
     * @param template (required) template location
     */
    public MessageTemplate(String name, String template) {
        this.name = name;
        this.templates = template;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return templates; }

    public void setLocation(String location) { this.templates = location; }
}
