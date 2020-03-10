package com.example.birthdayapp.service;


import com.example.birthdayapp.converter.EventRoomConverter;
import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.exception.NotFoundRoomException;
import com.example.birthdayapp.repository.EventRoomRepository;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventRoomService {

    private EventRoomRepository repository;
    private EventRoomConverter converter;

    @Autowired
    public EventRoomService(EventRoomRepository repository, EventRoomConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<EventRoomResource> getAll() {
        List<EventRoom> eventRooms = Optional.ofNullable(repository.findAll()).orElseThrow(() -> new NotFoundRoomException("There are not rooms with this parameters"));
        return converter.convert(eventRooms);

    }

    public void create(EventRoomResource eventRoomResource) {

    }
}
