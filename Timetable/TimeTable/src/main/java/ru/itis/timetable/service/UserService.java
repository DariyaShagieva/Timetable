package ru.itis.timetable.service;


import ru.itis.timetable.form.RecoveryForm;
import ru.itis.timetable.form.UserCreateForm;

public interface UserService {


    void signUp(UserCreateForm userCreateForm);

    boolean recovery(RecoveryForm recoveryForm);

}
