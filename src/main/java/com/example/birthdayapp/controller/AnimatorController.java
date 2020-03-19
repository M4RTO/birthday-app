package com.example.birthdayapp.controller;

import com.example.birthdayapp.anotations.CurrentUser;
import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.entity.User;
import com.example.birthdayapp.security.UserPrincipal;
import com.example.birthdayapp.service.AnimatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/animator")
public class AnimatorController {

    private AnimatorService service;

    @Autowired
    public AnimatorController(AnimatorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AnimatorResource> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AnimatorResource getOne(@PathVariable Long id){
        return service.findOne(id);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void createAnimator(@Valid @RequestBody AnimatorResource animatorResource, @CurrentUser UserPrincipal currentUser){
        service.create(animatorResource,currentUser);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void updateAnimator(@RequestBody AnimatorResource animatorResource){
        service.update(animatorResource);
    }





}
