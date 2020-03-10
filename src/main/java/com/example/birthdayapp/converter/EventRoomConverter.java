package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.entity.EventRoom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventRoomConverter {

    public List<EventRoomResource> convert(List<EventRoom> eventRoomList) {
       return eventRoomList.stream().map(this::convert).collect(Collectors.toList());
    }

    public EventRoomResource convert(EventRoom eventRoom){
        EventRoomResource eventRoomResource = new EventRoomResource();
        eventRoomResource.setCapacity(eventRoom.getCapacity());
        eventRoomResource.setId(eventRoom.getId());
        eventRoomResource.setName(eventRoom.getName());
        eventRoomResource.setRanking(eventRoom.getRanking());
        eventRoomResource.setSquareMeters(eventRoom.getSquareMeters());
        eventRoomResource.setScheduleAvailable(eventRoom.getScheduleAvailable());
        return eventRoomResource;
    }
}
