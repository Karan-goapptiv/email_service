package email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(EmailSend eParams) throws MessagingException {

        if (eParams.isHtml())
                sendHtmlMail(eParams);
        else {
            sendPlainTextMail(eParams);
        }

    }

    private void sendHtmlMail(EmailSend eParams) throws MessagingException {

        boolean isHtml = true;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
        helper.setReplyTo(eParams.getFrom());
        helper.setFrom(eParams.getFrom());
        helper.setSubject(eParams.getSubject());
        helper.setText(eParams.getMessage(), isHtml);

        if (eParams.getCc().size() > 0) {
            helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }

        mailSender.send(message);
    }

    private void sendPlainTextMail(EmailSend eParams) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        eParams.getTo().toArray(new String[eParams.getTo().size()]);
        mailMessage.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
        mailMessage.setReplyTo(eParams.getFrom());
        mailMessage.setFrom(eParams.getFrom());
        mailMessage.setSubject(eParams.getSubject());
        mailMessage.setText(eParams.getMessage());

        if (eParams.getCc().size() > 0) {
            mailMessage.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }

        mailSender.send(mailMessage);

    }

}