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
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;

    @OneToMany(mappedBy = "teacher")
    private List<ElectiveCourse> courses;

    @OneToOne
    @JoinColumn(name = "fk_timetable_id", referencedColumnName = "time_table_kfu_id")
    private TimeTable timeTable;

}
