package com.example.birthdayapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GuestResource {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;


}
