package ru.itis.services.timetable.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.services.timetable.form.RecoveryForm;
import ru.itis.services.timetable.form.UserCreateForm;
import ru.itis.services.timetable.mapper.UserMapper;
import ru.itis.services.timetable.model.user.User;
import ru.itis.services.timetable.repository.UserRepository;


import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean signUp(UserCreateForm userCreateForm) {
        Optional<User> userCandidate = userRepository.findByEmail(userCreateForm.getEmail());
        if(userCandidate.isPresent()) {
            return false; //проверка на уникальность email
        }
        userRepository.save(userMapper.userCreateFormToUser(userCreateForm));
        return true;
    }

    @Override
    public boolean recovery(RecoveryForm recoveryForm) {
        Optional<User> userCandidate = userRepository.findByEmail(recoveryForm.getEmail());
        if (userCandidate.isPresent()) {
            return true;
        }
        return false;
    }
}
