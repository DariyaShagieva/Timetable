package ru.itis.timetable.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "time_table_kfu")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_table_kfu_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "timeTable")
    private ElectiveCourse course;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "timeTable")
    private Teacher teacher;

    @Enumerated(value = EnumType.STRING)
    private Days day;

    @Column(name = "time_table_group")
    private String group;

    @Enumerated(value = EnumType.STRING)
    private Time time;

}
