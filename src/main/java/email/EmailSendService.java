package email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;


@Component
public class EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlMail(EmailSend eParams) throws MessagingException {

        boolean isHtml = true;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        // set recipients of the mail
        helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));

        //set reply to for the mail
        helper.setReplyTo(eParams.getFrom());

        // set sender of the mail
        helper.setFrom(eParams.getFrom());

        // set mail subject
        helper.setSubject(eParams.getSubject());

        // get template by template name
        EmailTemplate template = new EmailTemplate(eParams.getTemplateName());

        // parameters for template
        Map<String, String> replacements = new HashMap<String, String>();
        for (String data:eParams.getMessage()) {
            replacements.put("user",data);
            String text = template.getTemplate(replacements);
            helper.setText(text, isHtml);
        }

        // set Cc recipients of the mail
        if (eParams.getCc().size() > 0) {
            helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }

        // set Bcc recipients of the mail
        if(eParams.getBcc().size() > 0) {
            helper.setBcc(eParams.getBcc().toArray(new String[eParams.getBcc().size()]));
        }

        // sends the mail
        mailSender.send(message);
    }
}
