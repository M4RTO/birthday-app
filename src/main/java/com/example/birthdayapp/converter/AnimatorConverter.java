package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.entity.Animator;
import com.example.birthdayapp.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimatorConverter {

    public List<AnimatorResource> convert(List<Animator> animatorList) {
        return animatorList.stream().map(this::convert).collect(Collectors.toList());
    }

    public AnimatorResource convert(Animator animator){
        AnimatorResource resource = new AnimatorResource();
        resource.setCompany(animator.getCompany());
        resource.setName(animator.getName());
        resource.setRanking(animator.getRanking());
        resource.setSalaryPerHour(animator.getSalaryPerHour());
        resource.setScheduleAvailable(animator.getScheduleAvailable());
        resource.setId(animator.getId());
        return resource;
    }

    public Animator convert(AnimatorResource resource, User user) {
        Animator entity = new Animator();
        entity.setCompany(resource.getCompany());
        entity.setName(resource.getName());
        entity.setRanking(resource.getRanking());
        entity.setSalaryPerHour(resource.getSalaryPerHour());
        entity.setScheduleAvailable(resource.getScheduleAvailable());
        entity.setId(resource.getId());
        entity.setUser(user);
        return entity;
    }

    public void convert(Animator animator, AnimatorResource resource) {
        animator.setCompany(resource.getCompany());
        animator.setName(resource.getName());
        animator.setRanking(resource.getRanking());
        animator.setSalaryPerHour(resource.getSalaryPerHour());
        animator.setScheduleAvailable(resource.getScheduleAvailable());
    }
}
