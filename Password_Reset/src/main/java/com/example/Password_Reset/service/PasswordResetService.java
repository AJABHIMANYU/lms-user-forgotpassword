package com.example.Password_Reset.service;


import com.example.Password_Reset.Feign.UserServiceClient;
import com.example.Password_Reset.dto.UserDTO;
import com.example.Password_Reset.model.PasswordResetToken;
import com.example.Password_Reset.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;  // Assume an email service for sending reset emails

    @Autowired
    private UserServiceClient userServiceClient;

    public void generatePasswordResetToken(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email address must not be null or empty.");
        }

        UserDTO user = userServiceClient.getUserByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken(token, user.getUserId(), LocalDateTime.now().plusHours(2));

            System.out.println("Generated token: " + resetToken.getToken());
            System.out.println("Expiration date: " + resetToken.getExpirationDate());
            tokenRepository.save(resetToken);

            emailService.sendPasswordResetEmail(user.getUserMail(), token);  // Assume this service is implemented
        }
    }


    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElse(null);
        return resetToken != null && resetToken.getExpirationDate().isAfter(LocalDateTime.now());
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElse(null);
        if (resetToken != null && validatePasswordResetToken(token)) {
            userServiceClient.updatePassword(resetToken.getUserId(), newPassword);
            tokenRepository.deleteByToken(token);  // Clean up used token
        }
    }
    public  UserDTO getdetails(String email)
    {
        UserDTO user = userServiceClient.getUserByEmail(email);
        return user;

    }
}