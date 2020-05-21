package ru.itis.services.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.services.timetable.form.RecoveryForm;
import ru.itis.services.timetable.service.UserService;


import javax.validation.Valid;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    @Autowired
    private UserService userService;


    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Boolean> recovery(@Valid @RequestBody RecoveryForm recoveryForm) {
        Boolean check = userService.recovery(recoveryForm);
        //отправлять сообщение 
        return ResponseEntity.ok(check);
    }
}
