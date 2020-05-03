package ru.itis.KfuTimeTableBot.bot;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;


@Component
public class ChatBot extends AbilityBot {

    public static ChatBot of() {
      String botName = "KfuTimeTableBot";
      String botToken = "1198487245:AAGpEWF_9f3xHXwSzu5jBf5v--_4QIJaJug";
      ConfigurationBot configurationBot = ConfigurationBot.of(botToken, botName);
      return new ChatBot(configurationBot);
    }

    public ChatBot(ConfigurationBot configurationBot) {
      super(configurationBot.getBotToken(), configurationBot.getBotName(), configurationBot.getDefaultBotOptions());
    }


  @Override
  public int creatorId() {
    return 391486649;
  }
}
