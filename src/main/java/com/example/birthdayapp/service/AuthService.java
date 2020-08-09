package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.AuthConverter;
import com.example.birthdayapp.domain.*;
import com.example.birthdayapp.entity.Role;
import com.example.birthdayapp.entity.RoleName;
import com.example.birthdayapp.entity.User;
import com.example.birthdayapp.exception.AppException;
import com.example.birthdayapp.repository.RoleRepository;
import com.example.birthdayapp.repository.UserRepository;
import com.example.birthdayapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class AuthService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private AuthConverter converter;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, AuthConverter converter, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.converter = converter;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
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

    public ResponseEntity<JwtAuthenticationResponse> authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(converter.convert(jwt,authentication));

    }
}
