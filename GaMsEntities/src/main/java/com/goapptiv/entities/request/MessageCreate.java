package com.goapptiv.entities.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MessageCreate {

    @NotNull
    private String data;

    @NotNull
    private String templateName;

    @NotNull
    private List<String> to;

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getTemplateName() { return templateName; }

    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public List<String> getTo() { return to; }

    public void setTo(List<String> to) { this.to = to; }
}
