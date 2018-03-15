package email;

import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseMail {

    @Autowired
    EmailSendService emailSendService;

    public void sendHtmlMail(String From , String To, String Subject ) throws MessagingException {

        String from = From;
        String to = To;
        String subject = Subject;

        EmailTemplate template = new EmailTemplate("hello-world.html");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("today", String.valueOf(new Date()));

        String message = template.getTemplate(replacements);

        EmailSend emailSend = new EmailSend(from, to, subject, message);
        emailSend.setHtml(true);
        emailSendService.send(emailSend);
    }
}
