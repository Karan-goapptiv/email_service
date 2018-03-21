package com.example.email.message;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class SmsService {

    public String sendSms(String Template, String Numbers) {

        // get template by name
        SmsTemplate template = new SmsTemplate(Template);

        Map<String, String> replacements = new HashMap<String, String>();

        // parameters for template
        String text = template.getTemplate(replacements);

        try {
            // Construct data
            String apiKey = "apikey=" + "6R0ASq1cMzs-IkOs9afE3Ll5uYaWlzpEjLLpd7qDPJ";
            String message = "&message=" + text;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + Numbers;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}
