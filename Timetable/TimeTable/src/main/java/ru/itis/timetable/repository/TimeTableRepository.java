package ru.itis.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.timetable.model.TimeTable;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findAllByGroup(String group);
}
