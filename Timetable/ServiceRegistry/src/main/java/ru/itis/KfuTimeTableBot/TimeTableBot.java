package ru.itis.KfuTimeTableBot;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TimeTableBot extends TelegramLongPollingBot {

    private DefaultBotOptions defaultBotOptions;

    public final static String BOT_TOKEN = "1198487245:AAGpEWF_9f3xHXwSzu5jBf5v--_4QIJaJug";


    private final static String BOT_USERNAME = "KfuTimeTableBot";



    @Autowired
    public TimeTableBot(DefaultBotOptions defaultBotOptions) {
        this.defaultBotOptions = defaultBotOptions;
    }

   /* @Autowired
    protected TimeTableBot(String botToken, String botUsername, DefaultBotOptions defaultBotOPTIONS) {
        super(botToken, botUsername, defaultBotOPTIONS);
    }*/

   /* @Override
    public int creatorId() {
        return 391486649;
    }

    public Ability sayHelloWorld() {
        return Ability
                .builder()
                .name("start")
                .info("says hello world!")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> silent.send("Введите код", ctx.chatId()))
                .build();
    }*/

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(isUserCode(message.getText())) {

        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    private boolean isUserCode(String message) {
        if(message.contains("kfu:")) {
            String userCode = message.concat("kfu:");
            return true;
        } else return false;

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
