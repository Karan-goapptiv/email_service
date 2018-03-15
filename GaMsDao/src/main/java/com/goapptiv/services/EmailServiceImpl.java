package com.goapptiv.services;

import com.goapptiv.entities.Email;
import com.goapptiv.entities.QEmail;
import com.goapptiv.entities.QTemplate;
import com.goapptiv.entities.Template;
import com.goapptiv.services.base.EmailService;
import com.goapptiv.services.repositories.EmailRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EmailServiceImpl extends BaseServiceImp<Email> implements EmailService {

    /**
     * Email Repository
     */
    private final EmailRepository repository;

    /**
     * Email Service implementation
     */
    @Autowired
    public EmailServiceImpl(EmailRepository repository, EntityManager em) {
        super(em);
        this.repository = repository;
    }

    /**
     * Get list of email
     *
     * @param template (required) id of the email
     * @return Email List
     */
    @Override
    public List<Email> list(Template template) {
        // get base query
        QEmail qEmail = QEmail.email;
        QTemplate qTemplate = QTemplate.template;
        JPAQuery<Email> query = this.getBaseQuery(qEmail);

        query.where(qEmail.templateId.eq(template));

        return query.fetch();
    }
    /**
     * Save Email
     */
    @Override
    public Email save(Email email) { return this.repository.save(email); }
}