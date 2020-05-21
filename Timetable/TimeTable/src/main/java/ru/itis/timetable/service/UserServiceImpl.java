package ru.itis.timetable.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.timetable.form.RecoveryForm;
import ru.itis.timetable.form.UserCreateForm;
import ru.itis.timetable.mapper.UserMapper;
import ru.itis.timetable.model.user.User;
import ru.itis.timetable.model.user.UserRole;
import ru.itis.timetable.model.user.UserState;
import ru.itis.timetable.repository.UserRepository;


import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserCreateForm userCreateForm) {
        if(!userRepository.existsByEmail(userCreateForm.getEmail())) {
            userCreateForm.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
            User user = userMapper.userCreateFormToUser(userCreateForm);
            user.setUserRole(UserRole.USER);
            user.setUserState(UserState.ACTIVE);
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Login is already used");
        }

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
