package com.example.birthdayapp.repository;

import com.example.birthdayapp.entity.Animator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AnimatorRepository extends CrudRepository<Animator,Long> {

    List<Animator> findAll();
}
