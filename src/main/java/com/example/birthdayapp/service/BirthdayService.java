package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.BirthdayConverter;
import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.domain.BirthdayResource;
import com.example.birthdayapp.entity.Animator;
import com.example.birthdayapp.entity.BirthdayEvent;
import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.repository.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BirthdayService {

    private BirthdayRepository repository;
    private BirthdayConverter converter;
    private AnimatorService animatorService;
    private EventRoomService eventRoomService;

    @Autowired
    public BirthdayService(BirthdayRepository repository, BirthdayConverter converter, AnimatorService animatorService, EventRoomService eventRoomService) {
        this.repository = repository;
        this.converter = converter;
        this.animatorService = animatorService;
        this.eventRoomService = eventRoomService;
    }

    public void create(BirthdayResource resource) {
        Animator animator = animatorService.getAnimator(resource.getAnimator().getId());
        EventRoom eventRoom = eventRoomService.getEventRoom(resource.getAnimator().getId());
        BirthdayEvent entity = converter.convert(resource,animator,eventRoom);
        repository.save(entity);
    }
}
