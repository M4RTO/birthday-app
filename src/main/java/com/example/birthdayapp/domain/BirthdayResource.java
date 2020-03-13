package com.example.birthdayapp.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BirthdayResource {

    private Long id;
    private String birthdayName;
    private String birthdayYear;
    private Date birthdayDate;
    private List<GuestResource> guestList;
    private EventRoomResource eventRoom;
    private AnimatorResource animator;

}
