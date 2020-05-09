package ru.itis.KfuTimeTableBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.itis.KfuTimeTableBot.model.UserBot;
import ru.itis.KfuTimeTableBot.repository.UserBotRepository;
import ru.itis.KfuTimeTableBot.service.userTimetable.UserService;
import ru.itis.services.timetable.model.user.User;


import javax.persistence.EntityNotFoundException;

@Component
public class UserBotServiceImpl implements UserBotService {

    @Autowired
    private UserBotRepository userBotRepository;

    @Autowired
    private UserService userService;


    @Override
    public UserBot getByChatId(Long chatId) {
        return userBotRepository.findByChatId(chatId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveNewUserBot(Message message) {
        String code = message.getText().concat("kfu:");
        Long chatId = message.getChatId();
        UserBot userBot = getByChatId(chatId);
        User user = userService.getUserByTelegramCode(code);
        userBot.setUser(user);
        user.setTelegramCode(null);
        userBotRepository.save(userBot);
        userService.saveNewUser(user);
    }


}
