package com.example.birthdayapp.controller;

import com.example.birthdayapp.domain.BirthdayResource;
import com.example.birthdayapp.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/birthday")
public class EventBirthdayController {

    private BirthdayService service;

    @Autowired
    public EventBirthdayController(BirthdayService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBirthday(@RequestBody BirthdayResource resource) {
        service.create(resource);
    }

}
