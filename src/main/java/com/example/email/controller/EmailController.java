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
import email.EmailSend;
import email.EmailSendService;
import email.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.*;

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
    public Map<String, Object> create(@Validated @RequestBody EmailCreate request) throws MessagingException{

        // get template by name
        Template templateName = this.templateService.get(request.getTemplateName());

        // create new email
        Email email = new Email(request.getData(),templateName,request.getSubject());
       // email = this.emailService.save(email);

        List<String> mail = request.getTo();

        for (String to:mail) {
            this.sendHtmlMail("karanchavanknc@gmail.com", to, request.getSubject(),
                    request.getTemplateName());
        }
        // create new relation with email values for type to
        /*for (String value:request.getTo()) {
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
*/
        return this.done("email", email);
    }

    @Autowired
    EmailSendService emailSendService;

    public void sendHtmlMail(String From , String To, String Subject,String templateName ) throws MessagingException {

        String from = From;
        String to = To;
        String subject = Subject;
        String toCc = To;

        EmailTemplate template = new EmailTemplate(templateName+".html");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("today", String.valueOf(new Date()));

        String message = template.getTemplate(replacements);

        EmailSend emailSend = new EmailSend(from, to, toCc,subject, message);
        emailSend.setHtml(true);
        emailSendService.send(emailSend);
    }
}