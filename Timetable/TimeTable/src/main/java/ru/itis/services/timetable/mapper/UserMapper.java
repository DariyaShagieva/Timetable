package ru.itis.services.timetable.mapper;

import org.mapstruct.Mapper;
import ru.itis.services.timetable.form.UserCreateForm;
import ru.itis.services.timetable.model.user.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User userCreateFormToUser(UserCreateForm userCreateForm);


}
