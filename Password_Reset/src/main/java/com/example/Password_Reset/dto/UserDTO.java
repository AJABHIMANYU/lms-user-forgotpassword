package com.example.Password_Reset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Id
    private String userId;
    private String userName;
    private String userMail;
    private String userPassword;    // The user's password (hashed ideally, not raw password)
}