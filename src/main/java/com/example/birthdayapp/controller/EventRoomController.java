package com.example.birthdayapp.controller;

import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.service.EventRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event-room")
public class EventRoomController {

    private EventRoomService service;

    @Autowired
    public EventRoomController(EventRoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventRoomResource> getAll(){
        return service.getAll();
    }


    @GetMapping("/{id}")
    public EventRoomResource getOne(@PathVariable Long id){
        return service.findOne(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoomEvent(@RequestBody EventRoomResource eventRoomResource){
        service.create(eventRoomResource);
    }

    @PutMapping("/")
    public void updateRoomEvent(@RequestBody EventRoomResource eventRoomResource){
        service.update(eventRoomResource);
    }



}
