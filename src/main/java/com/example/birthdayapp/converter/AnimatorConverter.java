package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.AnimatorResource;
import com.example.birthdayapp.entity.Animator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
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
}
