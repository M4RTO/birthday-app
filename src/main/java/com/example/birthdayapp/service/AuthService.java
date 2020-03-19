package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.AuthConverter;
import com.example.birthdayapp.domain.ApiResponse;
import com.example.birthdayapp.domain.SignUpRequest;
import com.example.birthdayapp.domain.UserType;
import com.example.birthdayapp.entity.Role;
import com.example.birthdayapp.entity.RoleName;
import com.example.birthdayapp.entity.User;
import com.example.birthdayapp.exception.AppException;
import com.example.birthdayapp.repository.RoleRepository;
import com.example.birthdayapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Component
public class AuthService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;


    private AuthConverter converter;


    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, AuthConverter converter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.converter = converter;
    }

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        RoleName role = signUpRequest.getUserType().equals(UserType.USER_SERVICE) ? RoleName.ROLE_ADMIN : RoleName.ROLE_USER ;


        Role userRole = roleRepository.findByName(role).orElseThrow(() -> new AppException("User Role not set."));

        User user = converter.convert(signUpRequest,userRole);
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
