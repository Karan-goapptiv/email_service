package com.goapptiv.services.base;

import com.goapptiv.entities.MessageTemplate;
import com.goapptiv.entities.Template;

public interface MessageTemplateService {

    /**
     * Get MessageTemplate
     *
     * @param name (required) template name
     * @return messageTemplate
     */
    MessageTemplate get(String name);

}
