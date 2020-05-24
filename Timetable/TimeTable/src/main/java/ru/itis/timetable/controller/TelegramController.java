package ru.itis.timetable.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.timetable.dto.TelegramCodeDto;
import ru.itis.timetable.service.UserService;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    @Autowired
    private UserService userService;

    @ApiOperation("Get all telegramBot")
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TelegramCodeDto> getCode(@RequestHeader("Authorization") String authorization, @PathVariable String email) {
        return ResponseEntity.ok().body(userService.getCode(email));
    }
}
