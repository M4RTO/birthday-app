package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.SignUpRequest;
import com.example.birthdayapp.entity.Role;
import com.example.birthdayapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;



    public User convert(SignUpRequest signUpRequest, Role userRole) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(Collections.singleton(userRole));
        return user;

    }
}
