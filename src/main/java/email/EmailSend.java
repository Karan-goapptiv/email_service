package email;

import email.util.AppUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EmailSend {

    private String from;

    private List<String> to;

    private List<String> cc;

    private List<String> bcc;

    private String subject;

    private List<String> message;

    private String templateName;

    private boolean isHtml;

    /**
     * EmailSend constructor
     */
    public EmailSend() {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
        this.bcc = new ArrayList<String>();
        this.message = new ArrayList<String>();
    }

    /**
     * EmailSend constructor
     *
     * @param from (required) sender of the mail
     * @param toList (required) recipients of the mail
     * @param subject (required) subject of the mail
     * @param message (required) parameters for the template
     * @param templateName (required) html template for the mail
     */
    public EmailSend(String from, List<String> toList, String subject, List<String> message, String templateName) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.to = toList;
        this.templateName=templateName;
    }

    /**
     * EmailSend constructor
     *
     * @param from (required) sender of the mail
     * @param toList (required) recipients of the mail
     * @param ccList (required) recipients of the mail for type CC
     * @param bccList (required) recipients of the mail for type BCC
     * @param subject (required) subject of the mail
     * @param message (required) parameters for the template
     * @param templateName (required) html template for the mail
     */
    public EmailSend(String from, List<String> toList, List<String> ccList, List<String> bccList, String subject,
                     List<String> message,String templateName) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.templateName=templateName;
        this.to = toList;
        this.cc = ccList;
        this.bcc = bccList;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public List<String> getTo() { return to; }

    public void setTo(List<String> to) { this.to = to; }

    public List<String> getCc() { return cc; }

    public void setCc(List<String> cc) { this.cc = cc; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public List<String> getMessage() { return message; }

    public void setMessage(List<String> message) { this.message = message; }

    public List<String> getBcc() { return bcc; }

    public void setBcc(List<String> bcc) { this.bcc = bcc; }

    public String getTemplateName() { return templateName; }

    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }
}