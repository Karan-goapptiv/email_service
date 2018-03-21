package com.example.email.controller;

import com.example.email.controller.base.BaseController;
import com.example.email.message.SmsService;
import com.example.email.security.model.UserContext;
import com.goapptiv.entities.Message;
import com.goapptiv.entities.MessageTemplate;
import com.goapptiv.entities.MessageValue;
import com.goapptiv.entities.enums.Type;
import com.goapptiv.entities.request.MessageCreate;
import com.goapptiv.services.base.MessageService;
import com.goapptiv.services.base.MessageTemplateService;
import com.goapptiv.services.base.MessageValueService;
import javafx.scene.control.Separator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.sendgrid.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/v1/messages/send")
@RestController
public class MessageController extends BaseController {

    private MessageService messageService;
    private MessageValueService messageValueService;
    private MessageTemplateService messageTemplateService;

    @Autowired
    public MessageController(MessageService messageService, MessageValueService messageValueService,
                             MessageTemplateService messageTemplateService){
        this.messageService = messageService;
        this.messageValueService = messageValueService;
        this.messageTemplateService = messageTemplateService;
    }

    /**
     * Get list of messages
     *
     * @param templateName (required) template name of message
     * @param principal
     * @return messages
     */
    @RequestMapping(value = "/{templateName}", method = RequestMethod.GET)
    public Map<String, Object> index(@PathVariable String templateName, Principal principal){

        UserContext user = this.getUser(principal);
        MessageTemplate template = this.messageTemplateService.get(templateName);
        List<Message> messages = this.messageService.list(template);
        return this.done("messages", messages);
    }

    /**
     * Create message
     *
     * @param request Form fields
     * @return message
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody MessageCreate request){

        // get template by template name
        MessageTemplate template = this.messageTemplateService.get(request.getTemplateName());

        // create new message
        Message message = new Message(request.getData(),template);
        this.messageService.save(message);

        // create new message value
        for(String value:request.getTo()) {
            MessageValue messageValue = new MessageValue(value, Type.TO,message);
            this.messageValueService.save(messageValue);
        }

        StringBuilder rString = new StringBuilder();
        String sep=",";

        // get recipients for message
        for (String each : request.getTo()) {
            rString.append(each).append(sep);
        }

        // SmsService instance
        SmsService service = new SmsService();

        // sends message
        service.sendSms(template.getLocation(),rString.toString());
        return this.done("messages",message);
    }
}