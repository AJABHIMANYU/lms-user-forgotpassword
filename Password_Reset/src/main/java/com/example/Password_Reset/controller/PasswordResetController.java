package com.example.Password_Reset.controller;


import com.example.Password_Reset.dto.UserDTO;
import com.example.Password_Reset.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;


    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        passwordResetService.generatePasswordResetToken(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam("newPassword")String newPassword) {
        if (passwordResetService.validatePasswordResetToken(token)) {
            passwordResetService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password reset successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
    @GetMapping("/email")
    public ResponseEntity<UserDTO> requestemail(@RequestParam String email) {
        UserDTO u=passwordResetService.getdetails(email);
        return ResponseEntity.ok(u);
    }
}
