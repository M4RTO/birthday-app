package com.example.birthdayapp.converter;

import com.example.birthdayapp.domain.EventRoomResource;
import com.example.birthdayapp.entity.EventRoom;
import com.example.birthdayapp.entity.User;
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

    public EventRoom convert(EventRoomResource eventRoomResource, User currenUser) {
        EventRoom eventRoom = new EventRoom();
        eventRoom.setCapacity(eventRoomResource.getCapacity());
        eventRoom.setName(eventRoomResource.getName());
        eventRoom.setRanking(eventRoomResource.getRanking());
        eventRoom.setScheduleAvailable(eventRoomResource.getScheduleAvailable());
        eventRoom.setSquareMeters(eventRoomResource.getSquareMeters());
        eventRoom.setUser(currenUser);
        return eventRoom;
    }

    public void convert(EventRoom eventRoom, EventRoomResource resource) {
        eventRoom.setCapacity(resource.getCapacity());
        eventRoom.setName(resource.getName());
        eventRoom.setRanking(resource.getRanking());
        eventRoom.setScheduleAvailable(resource.getScheduleAvailable());
        eventRoom.setSquareMeters(resource.getSquareMeters());
    }
}
