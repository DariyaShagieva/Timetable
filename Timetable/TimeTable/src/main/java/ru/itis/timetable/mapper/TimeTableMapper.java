package ru.itis.timetable.mapper;


import org.springframework.stereotype.Component;
import ru.itis.timetable.dto.ScheduleTableDto;
import ru.itis.timetable.dto.TimeDto;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.model.TimeTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TimeTableMapper {

    public TimeTableDto convertModelToDto(TimeTable timeTable) {
        return TimeTableDto.builder()
                .title(timeTable.getCourse().getTitle())
                .teacher(timeTable.getTeacher().getFio())
                .shedule(createSchedule(timeTable))
                .build();
    }

    private ScheduleTableDto createSchedule(TimeTable timeTable) {
        String[] time = timeTable.getTime().getTime().split(":");
        TimeDto timeDto = TimeDto.builder()
                .hour(time[0])
                .minute(time[1])
                .build();
        return ScheduleTableDto.builder()
                .dayOfWeek(timeTable.getDay().ordinal() + 1)
                .times(timeDto).build();
    }

    private Stream<TimeTableDto> modelsToDtos(List<TimeTable> timeTableList) {
        return timeTableList.stream().map(this::convertModelToDto);
    }

    public List<TimeTableDto> convertModelsToDtos(List<TimeTable> timeTableList) {
        return modelsToDtos(timeTableList).collect(Collectors.toList());
    }
}
