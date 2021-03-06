package com.example.birthdayapp.service;


import com.example.birthdayapp.converter.EventRoomConverter;
import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.entity.User;
import com.example.birthdayapp.exception.ExistRoomException;
import com.example.birthdayapp.exception.NotFoundRoomException;
import com.example.birthdayapp.repository.EventRoomRepository;
import com.example.birthdayapp.security.CustomUserDetailsService;
import com.example.birthdayapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventRoomService {

    private EventRoomRepository repository;
    private EventRoomConverter converter;
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public EventRoomService(EventRoomRepository repository, EventRoomConverter converter, CustomUserDetailsService userDetailsService) {
        this.repository = repository;
        this.converter = converter;
        this.userDetailsService = userDetailsService;
    }

    public List<EventRoomResource> getAll() {
        List<EventRoom> eventRooms = Optional.ofNullable(repository.findAll()).orElseThrow(() -> new NotFoundRoomException("There are not rooms with this parameters"));
        return converter.convert(eventRooms);

    }

    public void create(EventRoomResource eventRoomResource, UserPrincipal currentUser) {
        existByName(eventRoomResource.getName());
        User user = userDetailsService.getUser(currentUser.getId());
        EventRoom entity = converter.convert(eventRoomResource,user);
        repository.save(entity);
    }

    private void existByName(String name){
        Boolean aBoolean = repository.existsByName(name);
        if(aBoolean) throw new ExistRoomException("There are a room event with this name");
    }

    public EventRoomResource findOne(Long id) {
        EventRoom eventRoom = getEventRoom(id);
        return converter.convert(eventRoom);
    }

    public EventRoom getEventRoom(Long id) {
        return repository.findById(id).orElseThrow(() -> new ExistRoomException("There are not room with this id"));
    }

    public void update(EventRoomResource resource) {
        EventRoom eventRoom = getEventRoom(resource.getId());
        converter.convert(eventRoom,resource);
        repository.save(eventRoom);

    }
}
