package ru.itis.KfuTimeTableBot.bot;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itis.KfuTimeTableBot.repository.UserBotRepository;
import ru.itis.KfuTimeTableBot.service.UpdateHandler;
import ru.itis.KfuTimeTableBot.service.UserBotService;


@Component
public class TimeTableBot extends TelegramLongPollingBot {

    public final static String BOT_TOKEN = "1198487245:AAGpEWF_9f3xHXwSzu5jBf5v--_4QIJaJug";


    private final static String BOT_USERNAME = "KfuTimeTableBot";



    @Autowired
    private UserBotService userBotService;


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message.getText().contains("kfu:")) {
            userBotService.saveNewUserBot(message);
        }

    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }



    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
