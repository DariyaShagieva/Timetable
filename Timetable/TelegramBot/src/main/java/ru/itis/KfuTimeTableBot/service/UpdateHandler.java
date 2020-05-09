package ru.itis.KfuTimeTableBot.service;


import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    void handlerUpdate(Update update);

}
