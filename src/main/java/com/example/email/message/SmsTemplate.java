package com.example.email.message;

import email.util.AppUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class SmsTemplate {

    private String templateName;

    private String template;

    private Map<String, String> replacementParams;

    public SmsTemplate(String templateName) {
        this.templateName = templateName;
        try {
            this.template = loadTemplate(templateName);
        } catch (Exception e) {
            this.template = null;
        }
    }

    private String loadTemplate(String templateName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();

        //get file from its path
        File file = new File(classLoader.getResource(templateName).getFile());
        String content;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new Exception("Could not read template with ID = " + templateName);
        }
        return content;
    }

    public String getTemplate(Map<String, String> replacements) {
        String cTemplate = this.getTemplate();

        if (!AppUtil.isObjectEmpty(cTemplate)) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }
        return cTemplate;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateId) {
        this.templateName = templateId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, String> getReplacementParams() {
        return replacementParams;
    }

    public void setReplacementParams(Map<String, String> replacementParams) {
        this.replacementParams = replacementParams; }
}
