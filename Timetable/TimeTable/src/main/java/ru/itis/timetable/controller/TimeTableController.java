package ru.itis.timetable.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.service.TimeTableService;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
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
    @GetMapping("/{group}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TimeTableDto>> getAllByGroup(@PathVariable String group) {
        return ResponseEntity.ok().body(timeTableService.getAllByGroup(group));
    }
  @ApiOperation("Get all timetable forms for group")
  @PostMapping("/save")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<TimeTableDto>> save() {
      timeTableService.saveNewTimeTable();
    return ResponseEntity.ok().build();
  }

    /*@ApiOperation("Get timetable form by id")
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TimeTableDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(timeTableService)
    }
*/




}
