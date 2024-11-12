package com.example.Password_Reset.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    private String id;
    @Field("token")
    private String token;
    @Field("user_id")
    private String userId;
    @Field("expiration_date")
    private LocalDateTime expirationDate;


    public PasswordResetToken(String token, String userId, LocalDateTime expirationDate) {

        this.token = token;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }
}
