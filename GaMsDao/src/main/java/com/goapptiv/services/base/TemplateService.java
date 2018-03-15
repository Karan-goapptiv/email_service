package com.goapptiv.services.base;

import com.goapptiv.entities.Template;

/**
 * Base class for templates service
 */
public interface TemplateService {

    /**
     * Get Template
     *
     * @param name (required) template name
     * @return template
     */
    Template get(String name);

    /**
     * Save Template
     *
     * @param template (required) template instance
     * @return template
     */
    Template save(Template template);
}
