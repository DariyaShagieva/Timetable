package ru.itis.timetable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.mapper.TimeTableMapper;
import ru.itis.timetable.model.TimeTable;
import ru.itis.timetable.parsers.Parser;
import ru.itis.timetable.repository.TimeTableRepository;

import java.io.InputStream;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeTableMapper timeTableMapper;

    @Autowired
    private Parser parser;


    @Override
    public List<TimeTableDto> getAllByGroup(String group) {
        List<TimeTable> timeTableList = timeTableRepository.findAllByGroup("11-804");
        return timeTableMapper.convertModelsToDtos(timeTableList);
    }

  @Override
  public Boolean saveNewTimeTable(InputStream inputStream) {
      parser.parseAndSave(inputStream);
      return true;
  }
}
