package com.example.birthdayapp.controller;

import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.domain.GuestResource;
import com.example.birthdayapp.service.AnimatorService;
import com.example.birthdayapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/guest")
public class GuestController {

    private GuestService service;

    @Autowired
    public GuestController(GuestService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<GuestResource> getAll(){
        return service.getAll();
    }

    public GuestResource getOne(@PathVariable Long id){
        return service.findOne(id);
    }


    @PutMapping("/")
    public void updateAnimator(@RequestBody GuestResource guestResource){
        service.update(guestResource);
    }





}
