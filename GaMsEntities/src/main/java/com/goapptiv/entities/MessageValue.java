package com.goapptiv.entities;

import com.goapptiv.entities.enums.Type;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGEVALUES")
public class MessageValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message messageId;

    /**
     * Default Constructor
     */
    public MessageValue() {}

    /**
     * MessageValue Constructor
     *
     * @param value (required) recipients of the message
     * @param type (required) type of the message
     * @param messageId (required) id of the message
     */
    public MessageValue(String value,Type type,Message messageId) {
        this.value = value;
        this.type = type;
        this.messageId = messageId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public Message getMessageId() { return messageId; }

    public void setMessageId(Message messageId) { this.messageId = messageId; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }
}
