package com.example.birthdayapp.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnimatorResource {

    private Long id;

    private String name;

    private String company;

    private Double salaryPerHour;

    private Integer ranking;

    private String scheduleAvailable;

}
