package ru.itis.services.timetable.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "timeTable")
    private ElectiveCourse course;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "timeTable")
    private Teacher teacher;

    private String day;

    private String group;

    private String time;
}
