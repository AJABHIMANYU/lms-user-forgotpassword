package com.example.Authentication_servevr.service;


import com.example.Authentication_servevr.Entity.UserEnt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



public class UserInfoUserDetails implements UserDetails {
    private String userMail;      // Email as the principal
    private String userPassword;  // Password

    public UserInfoUserDetails(UserEnt user) {
        this.userMail = user.getUserMail();       // Set email
        this.userPassword = user.getUserPassword(); // Set password
    }

    @Override
    public String getUsername() {
        return userMail; // Return userMail as the principal
    }

    @Override
    public String getPassword() {
        return userPassword; // Return userPassword
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
