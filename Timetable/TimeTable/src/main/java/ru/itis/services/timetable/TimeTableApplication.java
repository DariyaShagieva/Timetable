package ru.itis.services.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 07.04.2018
 * VkServiceApplication
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

@SpringBootApplication
public class TimeTableApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(TimeTableApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
