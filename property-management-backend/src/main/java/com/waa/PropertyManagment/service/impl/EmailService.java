package com.waa.PropertyManagment.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //@Value("${spring.mail.username}") private String sender;

    // Method 1
    // To send a simple email
    public void sendEmail(String to, String subject, String body)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("ayaleneh.yilma@gmail.com");
            mailMessage.setTo(to);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);
            // Sending the mail
            mailSender.send(mailMessage);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
        }
    }
}
