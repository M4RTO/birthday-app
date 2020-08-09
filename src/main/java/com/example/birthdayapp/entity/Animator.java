package com.example.birthdayapp.entity;

import com.example.birthdayapp.entity.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "animator",schema = "birthday_DB")
public class Animator extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "salary_per_hour")
    private Double salaryPerHour;

    @Column(name = "ranking")
    private Integer ranking;

    @Column(name = "schedule_available")
    private String scheduleAvailable;

    @OneToMany(mappedBy = "animator", fetch = FetchType.LAZY)
    private List<BirthdayEvent> birthdayEventList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
