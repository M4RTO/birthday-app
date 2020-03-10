package com.example.birthdayapp.controller;

import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.service.AnimatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animator")
public class AnimatorController {

    private AnimatorService service;

    @Autowired
    public AnimatorController(AnimatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<AnimatorResource> getAll(){
        return service.getAll();
    }




}
