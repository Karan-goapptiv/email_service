package com.example.email.controller;

import com.example.email.controller.base.BaseController;
import com.example.email.security.model.UserContext;
import com.goapptiv.entities.Email;
import com.goapptiv.entities.EmailValue;
import com.goapptiv.entities.Template;
import com.goapptiv.entities.enums.Type;
import com.goapptiv.entities.request.EmailCreate;
import com.goapptiv.services.base.EmailService;
import com.goapptiv.services.base.EmailValueService;
import com.goapptiv.services.base.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequestMapping("/v1/emails/send")
@RestController
public class EmailController extends BaseController{

    private final EmailService emailService;
    private final EmailValueService emailValueService;
    private final TemplateService templateService;

    /**
     * Constructor
     *
     * @param emailService EmailService instance
     * @param emailValueService EmailValueService instance
     * @param templateService TemplateService instance
     */
    @Autowired
    public EmailController(EmailService emailService, EmailValueService emailValueService,
                           TemplateService templateService) {
        this.emailService = emailService;
        this.emailValueService = emailValueService;
        this.templateService = templateService;
    }

    @RequestMapping(value = "/{templateName}", method = RequestMethod.GET)
    public Map<String, Object> index(@PathVariable String templateName,Principal principal){

        UserContext user = this.getUser(principal);
        Template template = this.templateService.get(templateName);
        List<Email> emails = this.emailService.list(template);
        return this.done("emails", emails);
    }

    /**
     * Create Email
     *
     * @param request (required) From Fields
     * @return Email
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody EmailCreate request) {

        // get template by name
        Template templateName = this.templateService.get(request.getTemplateName());

        // create new email
        Email email = new Email(request.getData(),templateName,request.getSubject());
        email = this.emailService.save(email);

        // create new relation with email values for type to
        for (String value:request.getTo()) {
            EmailValue emailValue = new EmailValue(value, Type.TO,email);
            this.emailValueService.save(emailValue);
        }

        // create new relation with email values for type cc
        for (String value:request.getCc()) {
            EmailValue emailValue = new EmailValue(value,Type.CC,email);
            this.emailValueService.save(emailValue);
        }

        // create new relation with email values for type bcc
        for (String value:request.getBcc()) {
            EmailValue emailValue = new EmailValue(value,Type.BCC,email);
            this.emailValueService.save(emailValue);
        }

        return this.done("email", email);
    }
}