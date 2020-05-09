package ru.itis.KfuTimeTableBot.service.userTimetable;

import ru.itis.services.timetable.model.user.User;

public interface UserService {

    User getUserByTelegramCode(String telegramCode);

    void saveNewUser(User user);
}
