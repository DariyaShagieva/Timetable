package ru.itis.services.timetable.service;


import ru.itis.services.timetable.dto.UserDto;
import ru.itis.services.timetable.form.LoginForm;
import ru.itis.services.timetable.model.user.User;

public interface LoginService {

    UserDto login(LoginForm loginForm);

    User getCurrentUser();
}
