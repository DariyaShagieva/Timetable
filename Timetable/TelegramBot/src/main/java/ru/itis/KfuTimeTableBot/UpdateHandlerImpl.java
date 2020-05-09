package ru.itis.KfuTimeTableBot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itis.KfuTimeTableBot.bot.TimeTableBot;
import ru.itis.KfuTimeTableBot.repository.UserBotRepository;


@Component
public class UpdateHandlerImpl implements UpdateHandler {

    @Autowired
    private UserBotRepository userBotRepository;

    private final TimeTableBot timeTableBot;

    public UpdateHandlerImpl(TimeTableBot timeTableBot) {
        this.timeTableBot = timeTableBot;
    }


    @Override
    public void handlerUpdate(Update update) {

    }

    @Override
    public boolean checkCode(Message message) {

        return false;
    }
}
