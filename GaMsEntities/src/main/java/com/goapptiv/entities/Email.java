package com.goapptiv.entities;

import com.goapptiv.entities.enums.Status;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMAILS")
public class Email implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    private String data;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template templateId;

    @Column
    private String subject;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.SENT;

    /**
     * Default Constructor
     */
    public Email(){}

    /**
     * Email Constructor
     *
     * @param data (required)
     * @param templateId (required) name of the template
     * @param subject (optional) subject of the mail
     */
    public Email(String data, Template templateId, String subject) {
        this.data = data;
        this.templateId = templateId;
        this.subject = subject;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public Template getTemplateId() { return templateId; }

    public void setTemplateId(Template templateId) { this.templateId = templateId; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
