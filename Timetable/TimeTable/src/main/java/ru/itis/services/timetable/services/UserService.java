package ru.itis.services.timetable.services;

import ru.itis.services.timetable.model.user.User;

public interface UserService {

    User getUserByTelegramCode(String telegramCode);

    void saveNewUser(User user);
}
