package ru.itis.timetable.service;


import ru.itis.timetable.dto.RecoveryDto;
import ru.itis.timetable.form.RecoveryForm;
import ru.itis.timetable.form.UserCreateForm;

public interface UserService {


    void signUp(UserCreateForm userCreateForm);

    RecoveryDto recovery(RecoveryForm recoveryForm);

}
