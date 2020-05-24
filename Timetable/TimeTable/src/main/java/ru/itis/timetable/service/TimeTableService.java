package ru.itis.timetable.service;

import ru.itis.timetable.dto.TimeTableDto;


import java.io.InputStream;
import java.util.List;

public interface TimeTableService {

    List<TimeTableDto> getAllByGroup(String group);

    Boolean saveNewTimeTable(InputStream inputStream);

   // TimeTableDto getById(Long id);
}
