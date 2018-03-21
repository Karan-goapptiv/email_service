package com.goapptiv.services;

import com.goapptiv.entities.MessageTemplate;
import com.goapptiv.entities.QMessageTemplate;
import com.goapptiv.entities.QTemplate;
import com.goapptiv.entities.Template;
import com.goapptiv.services.base.MessageTemplateService;
import com.goapptiv.services.repositories.MessageTemplateRepository;
import com.goapptiv.services.repositories.TemplateRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageTemplateServiceImpl extends BaseServiceImp<MessageTemplate> implements MessageTemplateService {

    /**
     * MessageTemplate Repository
     */
    private final MessageTemplateRepository repository;

    /**
     * MessageTemplate Service implementation
     */
    @Autowired
    public MessageTemplateServiceImpl(EntityManager em, MessageTemplateRepository repository) {
        super(em);
        this.repository = repository;
    }

    /**
     * Get MessageTemplate
     *
     * @param name (required) messageTemplate name
     * @return
     */
    @Override
    public MessageTemplate get(String name) {
        QMessageTemplate qMessageTemplate = QMessageTemplate.messageTemplate;

        // get base query
        JPAQuery<MessageTemplate> query = this.getBaseQuery(qMessageTemplate);

        // for template name
        query.where(qMessageTemplate.name.eq(name));

        return query.fetchFirst();
    }
}
