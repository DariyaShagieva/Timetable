package ru.itis.timetable.model;

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
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_table_kfu_id")
    private Long id;

    private String description;

    private String dayOfMonth;

    private String month;

   /* @ManyToOne
    @JoinColumn(name = "time_table_kfu_id")
    private TimeTable timeTable;
*/

}
