package com.goapptiv.services;

import com.goapptiv.entities.Message;
import com.goapptiv.entities.MessageTemplate;
import com.goapptiv.entities.QMessage;
import com.goapptiv.entities.QMessageTemplate;
import com.goapptiv.services.base.MessageService;
import com.goapptiv.services.repositories.MessageRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MessageServiceImpl extends BaseServiceImp<Message> implements MessageService {

    /**
     * Message Repository
     */
    private final MessageRepository repository;

    /**
     * Message Service implementation
     */
    @Autowired
    public MessageServiceImpl(MessageRepository repository, EntityManager em) {
        super(em);
        this.repository = repository;
    }

    /**
     * Get list of message
     *
     * @param messageTemplate (required) id of the message
     * @return Message List
     */
    @Override
    public List<Message> list(MessageTemplate messageTemplate) {
        // get base query
        QMessage qMessage = QMessage.message;
        QMessageTemplate qMessageTemplate = QMessageTemplate.messageTemplate;
        JPAQuery<Message> query = this.getBaseQuery(qMessage);

        query.where(qMessage.messageTemplateId.eq(messageTemplate));

        return query.fetch();
    }
    /**
     * Save Message
     */
    @Override
    public Message save(Message message) { return this.repository.save(message); }
}
