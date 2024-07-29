package com.github.AlissonMartin.ong.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String loadHtmlTemplate(String code) throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/email_confirmation.html");
        String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return content.replace("{{verification_code}}", code);
    }

    public void sendHtmlEmail(String receiver, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        emailSender.send(mimeMessage);
    }

    public void sendConfirmationEmail(String receiver, String code) throws MessagingException, IOException {
        String subject = "Confirmação de Email";
        String htmlContent = loadHtmlTemplate(code);

        sendHtmlEmail(receiver, subject, htmlContent);
    }
}
