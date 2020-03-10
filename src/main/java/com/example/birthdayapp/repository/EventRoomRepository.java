package com.example.birthdayapp.repository;

import com.example.birthdayapp.entity.EventRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface EventRoomRepository extends CrudRepository<EventRoom, Long> {

    @Override
    List<EventRoom> findAll();

    Boolean existsByName(String name);

    @Override
    Optional<EventRoom> findById(Long id);
}
