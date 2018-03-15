package com.goapptiv.entities.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EmailCreate {

    @NotNull
    private String data;

    @NotNull
    private String templateName;

    @NotNull
    private String subject;

    @NotNull
    private List<String> to;

    @NotNull
    private List<String> cc;

    @NotNull
    private List<String> bcc;

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getTemplateName() { return templateName; }

    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public List<String> getTo() { return to; }

    public void setTo(List<String> to) { this.to = to; }

    public List<String> getCc() { return cc; }

    public void setCc(List<String> cc) { this.cc = cc; }

    public List<String> getBcc() { return bcc; }

    public void setBcc(List<String> bcc) { this.bcc = bcc; }
}
