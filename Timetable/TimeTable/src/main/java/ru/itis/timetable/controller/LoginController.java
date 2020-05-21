package ru.itis.timetable.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.timetable.dto.UserDto;
import ru.itis.timetable.form.LoginForm;
import ru.itis.timetable.service.LoginService;


import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

   @Autowired
   private LoginService loginService;

    @PostMapping
    @ApiOperation("Login user")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}
