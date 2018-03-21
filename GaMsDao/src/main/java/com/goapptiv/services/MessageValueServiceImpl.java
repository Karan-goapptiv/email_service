package com.goapptiv.services;

import com.goapptiv.entities.MessageValue;
import com.goapptiv.services.base.MessageValueService;
import com.goapptiv.services.repositories.MessageValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageValueServiceImpl extends BaseServiceImp<MessageValue> implements MessageValueService {

    /**
     * Message value repository
     */
    private final MessageValueRepository repository;

    /**
     * Message Value Service implementation
     */
    @Autowired
    public MessageValueServiceImpl(EntityManager em, MessageValueRepository repository) {
        super(em);
        this.repository=repository;
    }
    /**
     * Save MessageValue
     *
     * @param messageValue (required) messageValue instance
     * @return
     */
    @Override
    public MessageValue save(MessageValue messageValue) {
        return this.repository.save(messageValue);
    }
}
