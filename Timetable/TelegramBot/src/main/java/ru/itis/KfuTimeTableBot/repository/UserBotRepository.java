package ru.itis.KfuTimeTableBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.KfuTimeTableBot.model.UserBot;

public interface UserBotRepository extends JpaRepository<UserBot, Long> {
}
