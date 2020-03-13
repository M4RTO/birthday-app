package com.example.birthdayapp.repository;

import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  GuestRepository extends CrudRepository<Guest,Long> {

    @Override
    List<Guest> findAll();

}
