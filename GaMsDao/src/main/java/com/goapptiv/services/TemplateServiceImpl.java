package com.goapptiv.services;

import com.goapptiv.entities.QTemplate;
import com.goapptiv.entities.Template;
import com.goapptiv.services.base.TemplateService;
import com.goapptiv.services.repositories.TemplateRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class TemplateServiceImpl extends BaseServiceImp<Template> implements TemplateService {

    /**
     * Templates Repository
     */
    private final TemplateRepository repository;

    /**
     * Template Service implementation
     */
    @Autowired
    public TemplateServiceImpl(EntityManager em, TemplateRepository repository) {
        super(em);
        this.repository = repository;
    }

    /**
     * Get Template
     *
     * @param name (required) template name
     * @return
     */
    @Override
    public Template get(String name) {
        QTemplate qTemplate = QTemplate.template;

        // get base query
        JPAQuery<Template> query = this.getBaseQuery(qTemplate);

        // for template name
        query.where(qTemplate.name.eq(name));

        return query.fetchFirst();
    }
}
