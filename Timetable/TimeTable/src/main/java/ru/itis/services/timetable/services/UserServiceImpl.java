package ru.itis.services.timetable.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.services.timetable.model.user.User;
import ru.itis.services.timetable.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
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
