package com.example.birthdayapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user",schema = "birthday_DB")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user")
    private BirthdayEvent birthdayEvent;

    @OneToOne(mappedBy = "user")
    private Animator animator;

    @OneToOne(mappedBy = "user")
    private EventRoom eventRoom;

}
