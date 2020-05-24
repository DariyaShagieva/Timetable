package ru.itis.timetable.service;

import ru.itis.timetable.dto.TimeTableDto;


import java.util.List;

public interface TimeTableService {

    List<TimeTableDto> getAllByGroup(String group);

    Boolean saveNewTimeTable();

   // TimeTableDto getById(Long id);
}
