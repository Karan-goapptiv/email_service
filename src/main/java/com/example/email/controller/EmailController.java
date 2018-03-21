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
    private final EmailSendService emailSendService;

    /**
     * Constructor
     *
     * @param emailService EmailService instance
     * @param emailValueService EmailValueService instance
     * @param templateService TemplateService instance
     */
    @Autowired
    public EmailController(EmailService emailService, EmailValueService emailValueService,
                           TemplateService templateService,EmailSendService emailSendService) {
        this.emailService = emailService;
        this.emailValueService = emailValueService;
        this.templateService = templateService;
        this.emailSendService = emailSendService;
    }

    /**
     * Get list of emails
     *
     * @param templateName (required) name of the template
     * @param principal
     * @return emails
     */
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
     * @param request (required) Form Fields
     * @return Email
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody EmailCreate request) throws MessagingException{

        // get template by name
        Template template = this.templateService.get(request.getTemplateName());

        // create new email
        Email email = new Email(request.getData(),template,request.getSubject());
        this.emailService.save(email);

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

        // get list of data
        List<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(splitByComma(request.getData())));

        // send mail with html template
        this.sendHtmlMail("karanchavanknc@gmail.com", request.getTo(),request.getCc(),request.getBcc(),
                request.getSubject(), template.getLocation(),data);

        return this.done("email", email);
    }

    /**
     * Sends mail
     *
     * @param from (required) sender of the mail
     * @param to (required) recipients of the mail
     * @param toCc (required) Cc recipients of the mail
     * @param toBcc (required) Bcc recipients of the mail
     * @param subject (required) subject of the mail
     * @param templateName (required) html template name
     * @param data (required) parameters for template
     * @throws MessagingException
     */
    public void sendHtmlMail(String from , List<String> to, List<String> toCc, List<String> toBcc, String subject,
                             String templateName, List<String> data) throws MessagingException {

        // pass values of email
        EmailSend emailSend = new EmailSend(from, to, toCc, toBcc, subject, data,templateName);

        //set html as true
        emailSend.setHtml(true);

        // send email
        emailSendService.sendHtmlMail(emailSend);
    }

    private String[] splitByComma(String toMultiple) {
        String[] toSplit = toMultiple.split(",");
        return toSplit;
    }
}