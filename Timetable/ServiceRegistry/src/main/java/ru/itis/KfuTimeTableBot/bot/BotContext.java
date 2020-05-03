package ru.itis.KfuTimeTableBot.bot;

import lombok.Builder;
import lombok.Data;
import ru.itis.KfuTimeTableBot.model.UserBot;

@Data
@Builder
public class BotContext {

    private final ChatBot bot;
    private final UserBot userBot;

    public static BotContext of(ChatBot bot, UserBot userBot) {
        return new BotContext(bot, userBot);
    }

    private BotContext(ChatBot bot, UserBot userBot) {
        this.bot = bot;
        this.userBot = userBot;
    }

}
