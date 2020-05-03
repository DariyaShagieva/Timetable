package ru.itis.KfuTimeTableBot.bot;

import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
@Data
@PropertySource("classpath:telegram.properties")
public class ConfigurationBot {

    @Value("${bot.proxy.host}")
    private static String HOST;

    @Value("${bot.proxy.port}")
    private static Integer PORT;

    private String botToken;
    private String botName;
    private DefaultBotOptions defaultBotOptions;

    public static ConfigurationBot of(String botName, String botToken) {
        return new ConfigurationBot(botToken, botName);
    }

    public ConfigurationBot(String botToken, String botName) {
        this.botName = botName;
        this.botToken = botToken;
        this.defaultBotOptions = setDefOpt();
    }

    private DefaultBotOptions setDefOpt() {
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        botOptions.setProxyHost(HOST);
        botOptions.setProxyPort(PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return botOptions;
    }


}
