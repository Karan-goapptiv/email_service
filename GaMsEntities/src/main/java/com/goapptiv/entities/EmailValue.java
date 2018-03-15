package com.goapptiv.entities;

import com.goapptiv.entities.enums.Type;

import javax.persistence.*;

@Entity
@Table(name = "EMAILVALUES")
public class EmailValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private Email emailId;

    /**
     * Default Constructor
     */
    public EmailValue() {}

    /**
     * EmailValue Constructor
     *
     * @param value (required) recipients of the mail
     * @param type (required) type of mail
     * @param emailId (required) id of the mail
     */
    public EmailValue(String value,Type type,Email emailId) {
        this.value = value;
        this.type = type;
        this.emailId = emailId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public Email getEmail_id() { return emailId; }

    public void setEmail_id(Email emailId) { this.emailId = emailId; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }
}
