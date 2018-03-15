package com.goapptiv.services.base;

import com.goapptiv.entities.Email;
import com.goapptiv.entities.QEmail;
import com.goapptiv.entities.Template;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

/**
 * Base class for email service
 */
public interface EmailService {

    /**
     * Get Email List
     */
    List<Email> list(Template template);

    /**
     * Save Email
     *
     * @param email (required) email instance to save
     * @return Email
     */
    Email save(Email email);
}