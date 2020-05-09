package ru.itis.KfuTimeTableBot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.itis.KfuTimeTableBot.model.UserBot;

public interface UserBotService {

    UserBot getByChatId(Long chatId);

    void saveNewUserBot(Message message);
}
