package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.BirthdayResource;
import com.example.birthdayapp.entity.Animator;
import com.example.birthdayapp.entity.BirthdayEvent;
import com.example.birthdayapp.entity.EventRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BirthdayConverter {

    private GuestConverter guestConverter;

    @Autowired
    public BirthdayConverter(GuestConverter guestConverter) {
        this.guestConverter = guestConverter;
    }

    public BirthdayEvent convert(BirthdayResource resource, Animator animator, EventRoom eventRoom) {
        BirthdayEvent entity = new BirthdayEvent();
        entity.setBirthdayName(resource.getBirthdayName());
        entity.setBirthdayDate(resource.getBirthdayDate());
        entity.setEventRoom(eventRoom);
        entity.setAnimator(animator);
        entity.setGuestList(resource.getGuestList().stream().map( g -> guestConverter.convert(g)).collect(Collectors.toList()));
        entity.setBirthdayYear(resource.getBirthdayYear());
        entity.setAnimator(animator);
        entity.setEventRoom(eventRoom);
        return entity;
    }
}
