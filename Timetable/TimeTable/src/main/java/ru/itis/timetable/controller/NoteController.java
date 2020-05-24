package ru.itis.timetable.controller;



import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.timetable.form.CreatNoteForm;
import ru.itis.timetable.service.NoteService;

import javax.validation.Valid;


@RestController
@RequestMapping("/event")
public class NoteController {

    @Autowired
    private NoteService noteService;



    @ApiOperation("Create note")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public void add(@RequestHeader("Authorization") String authorization,
                    @Valid @RequestBody CreatNoteForm createForm) {

    }





}
