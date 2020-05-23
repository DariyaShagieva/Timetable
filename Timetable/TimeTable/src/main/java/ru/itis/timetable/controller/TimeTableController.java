package ru.itis.timetable.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.service.TimeTableService;

import java.util.List;

@RestController
@RequestMapping("/")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @ApiOperation("Get all timetable forms for default group")
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TimeTableDto>> getAllByDefaultGroup() {
        return ResponseEntity.ok().body(timeTableService.getAllByGroup("11-703"));
    }

    @ApiOperation("Get all timetable forms for group")
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TimeTableDto>> getAllByGroup(@PathVariable String group) {
        return ResponseEntity.ok().body(timeTableService.getAllByGroup(group));
    }

  /*  @ApiOperation("Get timetable form by id")
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TimeTableDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(timeTableService)
    }*/



}
