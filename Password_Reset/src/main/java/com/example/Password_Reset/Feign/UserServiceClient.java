package com.example.Password_Reset.Feign;



import com.example.Password_Reset.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-servevr", url = "http://localhost:7070/api/auth")
public interface UserServiceClient {

    // Endpoint to fetch user by email
    @GetMapping("/findByEmail")
    public UserDTO getUserByEmail(@RequestParam String email);

    // Endpoint to update password by user ID
    @PutMapping("/updatePassword")
    public void updatePassword(@RequestParam String userId, @RequestParam String newPassword);

}