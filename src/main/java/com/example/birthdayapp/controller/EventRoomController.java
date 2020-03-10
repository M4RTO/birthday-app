package com.example.birthdayapp.controller;

import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.service.EventRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/event-room")
public class EventRoomController {

    private EventRoomService service;

    @Autowired
    public EventRoomController(EventRoomService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<EventRoomResource> getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public void createRoomEvent(EventRoomResource eventRoomResource){
        service.create(eventRoomResource);
    }



}
