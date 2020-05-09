package ru.itis.KfuTimeTableBot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.services.timetable.model.user.User;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class   UserBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private Integer stateId;

    @OneToOne
    @JoinColumn(name = "user_bot_id", referencedColumnName = "user_timetable_id")
    private User user;

}
