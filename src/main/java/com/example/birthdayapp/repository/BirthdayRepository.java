package com.example.birthdayapp.repository;

import com.example.birthdayapp.entity.BirthdayEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthdayRepository extends CrudRepository<BirthdayEvent,Long> {
}
