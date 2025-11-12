package com.cristian.moneymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${BREVO_API_KEY}")
    private String brevoApiKey;

    @Value("${app.mail.from}")
    private String fromEmail;

    /**
     * Envía un correo simple (HTML o texto) usando la API de Brevo
     */
    public void sendEmail(String to, String subject, String htmlBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", brevoApiKey);

            String body = String.format("""
            {
              "sender": { "name": "MoneyManager", "email": "%s" },
              "to": [{"email": "%s"}],
              "subject": "%s",
              "htmlContent": "%s"
            }
            """, fromEmail, to, subject, htmlBody);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            restTemplate.postForEntity(
                    "https://api.brevo.com/v3/smtp/email",
                    entity,
                    String.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    /**
     * Envía un correo con attachments
     */
    public void sendEmailWithAttachment(String to, String subject, String htmlBody, byte[] attachment, String filename) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", brevoApiKey);

            // Convertimos el archivo a Base64
            String encodedFile = Base64.getEncoder().encodeToString(attachment);

            // Construimos el JSON para la API de Brevo
            String body = String.format("""
            {
              "sender": { "name": "MoneyManager", "email": "%s" },
              "to": [{"email": "%s"}],
              "subject": "%s",
              "htmlContent": "%s",
              "attachment": [
                {
                  "name": "%s",
                  "content": "%s"
                }
              ]
            }
            """, fromEmail, to, subject, htmlBody, filename, encodedFile);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            restTemplate.postForEntity(
                    "https://api.brevo.com/v3/smtp/email",
                    entity,
                    String.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email with attachment: " + e.getMessage(), e);
        }
    }
}
