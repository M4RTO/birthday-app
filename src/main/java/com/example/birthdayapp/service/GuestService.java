package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.EventRoomConverter;
import com.example.birthdayapp.converter.GuestConverter;
import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.domain.GuestResource;
import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.entity.Guest;
import com.example.birthdayapp.exception.ExistRoomException;
import com.example.birthdayapp.exception.NotFoundGuestException;
import com.example.birthdayapp.exception.NotFoundRoomException;
import com.example.birthdayapp.repository.EventRoomRepository;
import com.example.birthdayapp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GuestService {

    private GuestRepository repository;
    private GuestConverter converter;

    @Autowired
    public GuestService(GuestRepository repository, GuestConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<GuestResource> getAll() {
        List<Guest> guestList = Optional.ofNullable(repository.findAll()).orElseThrow(() -> new NotFoundGuestException("There are not guest with this parameters"));
        return converter.convert(guestList);

    }

    public GuestResource findOne(Long id) {
        Guest guest = getGuest(id);
        return converter.convert(guest);
    }

    private Guest getGuest(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundGuestException("There are not guest with this id"));
    }

    public void update(GuestResource resource) {
        Guest guest = getGuest(resource.getId());
        converter.convert(guest,resource);
        repository.save(guest);
    }
}
