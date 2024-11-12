package com.example.Authentication_servevr.controller;


import com.example.Authentication_servevr.Entity.UserEnt;

import com.example.Authentication_servevr.repo.UserRepo;
import com.example.Authentication_servevr.service.JwtService;
import com.example.Authentication_servevr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public UserEnt register(@RequestBody UserEnt user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getToken(@RequestBody UserEnt user) {
        System.out.println("user : " + user);

        UserEnt olduser=userRepo.findByUserMail(user.getUserMail()).get();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserMail(), user.getUserPassword()));
        System.out.println("authenticated?? : " + authenticate.isAuthenticated());
        if(authenticate.isAuthenticated()) {
            String token= jwtService.generateToken(user.getUserMail());
            Map<String, Object> response = new HashMap<>();
            response.put("id", olduser.getUserId());        // Assuming `getUserId()` is a method in UserCredentialsEntity
            response.put("token", token);                // Add the generated token
            response.put("username", olduser.getUserName());
            response.put("userMail", olduser.getUserMail());
            System.out.println("USer resposne"+response);
            return ResponseEntity.ok(response);
        }

        return null;
    }
    @GetMapping("/validate/token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            try {
                boolean isValid = userService.verifyToken(token);
                return ResponseEntity.ok(isValid);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format or signature");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header is missing or invalid");
        }
    }


    @GetMapping("/sample")
    public String sample() {
        return "Success";
    }

    // Endpoint to fetch user by email
    @GetMapping("/findByEmail")
    public UserEnt getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);

    };

    // Endpoint to update password by user ID
    @PutMapping("/updatePassword")
    public void updatePassword(@RequestParam String userId, @RequestParam String newPassword)
    {
        userService.updatePassword(userId, newPassword);
    }

}
