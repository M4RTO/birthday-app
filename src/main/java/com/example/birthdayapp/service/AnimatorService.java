package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.AnimatorConverter;
import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.entity.Animator;
import com.example.birthdayapp.repository.AnimatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimatorService {

    private AnimatorRepository repository;
    private AnimatorConverter converter;


    @Autowired
    public AnimatorService(AnimatorRepository repository, AnimatorConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<AnimatorResource> getAll() {
        List<Animator> animatorList = repository.findAll();
        return converter.convert(animatorList);
    }

}
