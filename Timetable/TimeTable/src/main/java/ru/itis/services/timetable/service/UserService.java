package ru.itis.services.timetable.service;


import ru.itis.services.timetable.form.RecoveryForm;
import ru.itis.services.timetable.form.UserCreateForm;

public interface UserService {


    boolean signUp(UserCreateForm userCreateForm);

    boolean recovery(RecoveryForm recoveryForm);

}
