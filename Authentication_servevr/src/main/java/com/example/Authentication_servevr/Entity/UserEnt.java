package com.example.Authentication_servevr.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Builder
public class UserEnt {
    @Id
    private String userId;         // Unique identifier for the
    private String userName;
    private String userMail;
    private String userPassword;

}
