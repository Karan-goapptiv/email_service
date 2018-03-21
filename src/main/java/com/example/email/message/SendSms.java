package com.example.email.message;

public class SendSms {

    private String template;

    private String numbers;

    /**
     * SendSms constructor
     *
     * @param templateName (required) html template name
     * @param Numbers (required) recipients of message
     */
    public SendSms(String templateName, String Numbers){
        this.template = templateName;
        this.numbers = Numbers;
    }

    public String getTemplate() { return template; }

    public void setTemplate(String template) { this.template = template; }

    public String getNumbers() { return numbers; }

    public void setNumbers(String numbers) { this.numbers = numbers; }
}
