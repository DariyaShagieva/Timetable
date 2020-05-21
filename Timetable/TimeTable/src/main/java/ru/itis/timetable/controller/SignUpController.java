package ru.itis.timetable.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.timetable.form.UserCreateForm;
import ru.itis.timetable.service.UserService;


import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserCreateForm userCreateForm) {
        userService.signUp(userCreateForm);
    }
}
