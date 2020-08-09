package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.JwtAuthenticationResponse;
import com.example.birthdayapp.domain.RolesUser;
import com.example.birthdayapp.domain.SignUpRequest;
import com.example.birthdayapp.entity.Role;
import com.example.birthdayapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    public JwtAuthenticationResponse convert(String jwt, Authentication authentication) {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setAccessToken(jwt);
        response.setUserType(convert(authentication.getAuthorities()));
        return response;
    }

    private List<RolesUser> convert(Collection<? extends GrantedAuthority> authorities) {
        List<RolesUser> rolesUserList = new ArrayList<>();
        authorities.forEach(a -> {
            RolesUser rolesUser = new RolesUser();
            rolesUser.setRole(a.toString());
            rolesUserList.add(rolesUser);
        });
        return rolesUserList;
    }
}
