package ru.itis.KfuTimeTableBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UpdateHandlerImpl implements UpdateHandler {

    private final TimeTableBot timeTableBot;

    public UpdateHandlerImpl(TimeTableBot timeTableBot) {
        this.timeTableBot = timeTableBot;
    }


    @Override
    public void handlerUpdate(Update update) {

    }
}
