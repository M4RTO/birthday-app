package com.example.birthdayapp.entity;

import com.example.birthdayapp.entity.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "event_room",schema = "birthday_DB")
public class EventRoom extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ranking")
    private Integer ranking;

    @Column(name = "schedule_available")
    private String scheduleAvailable;

    @Column(name = "square_meters")
    private String squareMeters;

    @Column(name = "capacity")
    private Long capacity;

    @OneToOne(mappedBy = "eventRoom")
    private BirthdayEvent birthdayEvent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
