package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.GuestResource;
import com.example.birthdayapp.entity.Guest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuestConverter {

    public List<GuestResource> convert(List<Guest> guestList) {
        return guestList.stream().map(this::convert).collect(Collectors.toList());
    }

    public GuestResource convert(Guest entity){
        GuestResource resource = new GuestResource();
        resource.setFirstName(entity.getFirstName());
        resource.setLastName(entity.getLastName());
        resource.setPhone(entity.getPhone());
        resource.setId(entity.getId());
        return resource;
    }

    public Guest convert(GuestResource resource) {
        Guest entity = new Guest();
        entity.setFirstName(resource.getFirstName());
        entity.setLastName(resource.getLastName());
        entity.setPhone(resource.getPhone());
        entity.setId(resource.getId());
        return entity;
    }

    public void convert(Guest entity, GuestResource resource) {
        entity.setFirstName(resource.getFirstName());
        entity.setLastName(resource.getLastName());
        entity.setPhone(resource.getPhone());
        entity.setId(resource.getId());
    }
}
