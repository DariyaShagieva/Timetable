package ru.itis.timetable.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.timetable.dto.TimeTableDto;
import ru.itis.timetable.service.TimeTableService;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @ApiOperation("Get all timetable forms for default group")
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TimeTableDto>> getAllByDefaultGroup() {
        String group = "11-703";
        return ResponseEntity.ok().body(timeTableService.getAllByGroup(group));
    }

    @ApiOperation("Get all timetable forms for group")
    @GetMapping("/{group}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TimeTableDto>> getAllByGroup(@PathVariable String group) {
        return ResponseEntity.ok().body(timeTableService.getAllByGroup(group));
    }
  @SneakyThrows
  @ApiOperation("Upload timeTable")
  @PostMapping("/save")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<TimeTableDto>> save(MultipartFile file) {
      timeTableService.saveNewTimeTable(file.getInputStream());
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
