package ru.itis.KfuTimeTableBot;


import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    void handlerUpdate(Update update);

    boolean checkCode(Message message);
}
