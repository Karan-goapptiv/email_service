package com.goapptiv.services.base;

import com.goapptiv.entities.MessageValue;

public interface MessageValueService {

    /**
     * Save MessageValue
     *
     * @param messageValue (required) messageValue instance
     * @return messageValue
     */
    MessageValue save(MessageValue messageValue);
}
