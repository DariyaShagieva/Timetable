package ru.itis.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.timetable.form.UserCreateForm;
import ru.itis.timetable.model.user.User;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User userCreateFormToUser(UserCreateForm userCreateForm);


}
