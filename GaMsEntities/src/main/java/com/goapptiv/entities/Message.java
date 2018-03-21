package com.goapptiv.entities;

import com.goapptiv.entities.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    private String data;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private MessageTemplate messageTemplateId;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.SENT;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * Default Constructor
     */
    public Message(){}

    /**
     * Message Constructor
     *
     * @param data (required)
     * @param messageTemplateId (required) name of the template
     */
    public Message(String data, MessageTemplate messageTemplateId) {
        this.data = data;
        this.messageTemplateId = messageTemplateId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public MessageTemplate getMessageTemplateId() { return messageTemplateId; }

    public void setMessageTemplateId(MessageTemplate messageTemplateId) { this.messageTemplateId = messageTemplateId; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
