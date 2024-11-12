package com.example.Password_Reset.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String email, String token) {

            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Recipient email address must not be null or empty.");
            }
            // Email sending logic here
        else
        {
            String url = "https://yourapp.com/reset-password?token=" + token;
            String message = "Click the link to reset your password: " + url;

            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setTo(email);
            emailMessage.setSubject("Password Reset Request");
            emailMessage.setText(message);

            mailSender.send(emailMessage);
        }
            }




}
