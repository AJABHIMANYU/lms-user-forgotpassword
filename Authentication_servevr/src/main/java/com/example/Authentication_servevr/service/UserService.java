package com.example.Authentication_servevr.service;


import com.example.Authentication_servevr.Entity.UserEnt;
import com.example.Authentication_servevr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEnt register(UserEnt user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword())); // Encrypt password
        return userRepo.save(user);
    }

    public boolean verifyToken(String token) {
        jwtService.validateToken(token);
        return true;
    }

    public UserEnt getUserByEmail(String email)
    {
        return userRepo.findByUserMail(email).orElse(null);
    }

    // Update password for a given user ID
    public void updatePassword(String userId, String newPassword) {
        Optional<UserEnt> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            UserEnt user = userOptional.get();
            user.setUserPassword(newPassword);  // Assume password is hashed before saving
            userRepo.save(user);
        }
    }
}
