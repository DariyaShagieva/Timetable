package ru.itis.timetable.service;


import ru.itis.timetable.dto.UserDto;
import ru.itis.timetable.form.LoginForm;
import ru.itis.timetable.model.user.User;

public interface LoginService {

    UserDto login(LoginForm loginForm);

    User getCurrentUser();
}
