package com.goapptiv.services.base;

import com.goapptiv.entities.EmailValue;

/**
 * Base class for email value service
 */
public interface EmailValueService {

    /**
     * Save EmailValue
     *
     * @param emailValue (required) emailValue instance
     * @return emailValue
     */
    EmailValue save(EmailValue emailValue);
}
