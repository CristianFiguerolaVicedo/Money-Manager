package com.cristian.moneymanager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    @Value("${BREVO_SENDER_EMAIL:no-reply@moneymanager.com}")
    private String senderEmail;

    public void sendEmail(String toAddress, String subject, String body) {
        String url = "https://api.brevo.com/v3/smtp/email";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> payload = Map.of(
                "sender", Map.of("email", senderEmail, "name", "Money Manager"),
                "to", new Map[]{ Map.of("email", toAddress) },
                "subject", subject,
                "htmlContent", "<p>" + body + "</p>"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("✅ Email enviado a " + toAddress + " (status " + response.getStatusCode() + ")");
        } catch (Exception e) {
            System.err.println("❌ Error enviando correo: " + e.getMessage());
            throw new RuntimeException("Error al enviar correo: " + e.getMessage());
        }
    }
}
