package com.example.birthdayapp.entity;

import com.example.birthdayapp.entity.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "birthday_event",schema = "birthday_DB")
public class BirthdayEvent extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birthday_name")
    private String birthdayName;

    @Column(name = "birthday_year")
    private String birthdayYear;

    @Column(name = "birthday_date")
    private Date birthdayDate;

    @OneToMany(mappedBy = "birthdayEvent", fetch = FetchType.LAZY)
    private List<Guest> guestList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_room_id")
    private EventRoom eventRoom;

    @ManyToOne
    @JoinColumn(name = "animator_id")
    private Animator animator;




}
