package ru.itis.timetable.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.timetable.dto.TimeDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleForm {

    private Integer dayOfWeek;

    private Integer duration;

    private String dayOfMonth;

    private String month;

    private String durationUnit;

    private TimeDto times;
}
