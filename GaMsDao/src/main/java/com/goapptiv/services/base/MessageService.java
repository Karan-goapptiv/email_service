package com.goapptiv.services.base;

import com.goapptiv.entities.Message;
import com.goapptiv.entities.MessageTemplate;

import java.util.List;

public interface MessageService {

    /**
     * Get Message List
     */
    List<Message> list(MessageTemplate messageTemplate);

    /**
     * Save Message
     *
     * @param message (required) email instance to save
     * @return message
     */
    Message save(Message message);
}
