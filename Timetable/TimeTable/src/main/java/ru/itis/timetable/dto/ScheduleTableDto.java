package ru.itis.timetable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ScheduleTableDto {

    private int dayOfWeek;

    private TimeDto times;

    private Integer duration;

    private String durationUnit;


}
