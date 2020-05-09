package ru.itis.KfuTimeTableBot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.KfuTimeTableBot.model.UserBot;

import java.util.Optional;


public interface UserBotRepository extends JpaRepository<UserBot, Long> {

    Optional<UserBot> findByChatId(Long chatId);

}
