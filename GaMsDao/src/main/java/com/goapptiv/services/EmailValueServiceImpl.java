package com.goapptiv.services;

import com.goapptiv.entities.EmailValue;
import com.goapptiv.services.base.EmailValueService;
import com.goapptiv.services.repositories.EmailValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class EmailValueServiceImpl extends BaseServiceImp<EmailValue> implements EmailValueService {

    /**
     * Email value repository
     */
    private final EmailValueRepository repository;

    /**
     * Email Value Service implementation
     */
    @Autowired
    public EmailValueServiceImpl(EntityManager em, EmailValueRepository repository) {
        super(em);
        this.repository=repository;
    }
    /**
     * Save EmailValue
     *
     * @param emailValue (required) emailValue instance
     * @return
     */
    @Override
    public EmailValue save(EmailValue emailValue) {
        return this.repository.save(emailValue);
    }
}
