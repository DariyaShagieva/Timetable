package ru.itis.KfuTimeTableBot.service.userTimetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.KfuTimeTableBot.repository.userTimetable.UserRepository;
import ru.itis.services.timetable.model.user.User;


import javax.persistence.EntityNotFoundException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByTelegramCode(String telegramCode) {
        return userRepository.findByTelegramCode(telegramCode).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveNewUser(User user) {
        userRepository.save(user);
    }


}
