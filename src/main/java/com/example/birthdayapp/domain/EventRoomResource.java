package com.example.birthdayapp.domain;



public class EventRoomResource {

    private Long id;
    private String name;
    private Integer ranking;
    private String scheduleAvailable;
    private String squareMeters;
    private Long capacity;
    //private BirthdayEvent birthdayEvent;

   // private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getScheduleAvailable() {
        return scheduleAvailable;
    }

    public void setScheduleAvailable(String scheduleAvailable) {
        this.scheduleAvailable = scheduleAvailable;
    }

    public String getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(String squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
