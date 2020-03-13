package com.example.birthdayapp.service;

import com.example.birthdayapp.converter.AnimatorConverter;
import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.entity.Animator;
import com.example.birthdayapp.exception.ExistAnimatorException;
import com.example.birthdayapp.exception.NotFoundAnimatorException;
import com.example.birthdayapp.repository.AnimatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        List<Animator> animatorList = Optional.ofNullable(repository.findAll()).orElseThrow(() -> new NotFoundAnimatorException(""));
        return converter.convert(animatorList);
    }


    public void create(AnimatorResource animatorResource) {
        Animator entity = converter.convert(animatorResource);
        repository.save(entity);
    }

    public AnimatorResource findOne(Long id) {
        Animator animator = getAnimator(id);
        return converter.convert(animator);
    }

    private Animator getAnimator(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundAnimatorException("There are not Animator with this id"));
    }

    public void update(AnimatorResource resource) {
        Animator animator = getAnimator(resource.getId());
        converter.convert(animator,resource);
        repository.save(animator);

    }
}
