package ru.itis.timetable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.model.TimeTable;
import ru.itis.timetable.repository.TimeTableRepository;

import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;


    @Override
    public List<TimeTableDto> getAllByGroup(String group) {
        List<TimeTable> timeTableList = timeTableRepository.findAllByGroup(group);



        return null;
    }
}
