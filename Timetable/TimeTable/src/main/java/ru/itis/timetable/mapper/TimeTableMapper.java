package ru.itis.timetable.mapper;


import org.springframework.stereotype.Component;
import ru.itis.timetable.dto.ScheduleDto;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.model.Days;
import ru.itis.timetable.model.TimeTable;

@Component
public class TimeTableMapper {

    public TimeTableDto convertModelToDto(TimeTable timeTable) {
        return TimeTableDto.builder()
                .title(timeTable.getCourse().getTitle())
                .teacher(timeTable.getTeacher().getFio())
                .shedule()
    }

    private ScheduleDto createSchedule(TimeTable timeTable) {
        String day = timeTable.getDay().getDay();
        Days.valueOf(day).

        return ScheduleDto.builder()
                .dayOfWeek(timeTable.getDay())
    }
}
