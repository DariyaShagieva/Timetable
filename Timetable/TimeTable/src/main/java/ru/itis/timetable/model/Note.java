package ru.itis.timetable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.itis.timetable.dto.TimeDto;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_table_kfu_id")
    private Long id;

    @Column(length = 3000)
    private String description;

    private Integer dayOfWeek;

    private Integer duration;

    private String dayOfMonth;

    private String month;

    private String durationUnit;

    private String times;

    @Column(name = "user_group")
    private String group;

}
