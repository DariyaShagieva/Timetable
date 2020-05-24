package ru.itis.timetable.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.timetable.dto.RecoveryDto;
import ru.itis.timetable.dto.TelegramCodeDto;
import ru.itis.timetable.form.RecoveryForm;
import ru.itis.timetable.form.UserCreateForm;
import ru.itis.timetable.mapper.UserMapper;
import ru.itis.timetable.model.user.User;
import ru.itis.timetable.model.user.UserRole;
import ru.itis.timetable.model.user.UserState;
import ru.itis.timetable.repository.UserRepository;


import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

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
            user.setTelegramCode("kfu:" + UUID.randomUUID().toString());
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Login is already used");
        }

    }

    @Override
    public RecoveryDto recovery(RecoveryForm recoveryForm) {
        RecoveryDto recoveryDto = new RecoveryDto();
        if (userRepository.existsByEmail(recoveryForm.getEmail())) {
            recoveryDto.setCheck(true);
        } else {
            recoveryDto.setCheck(false);
        }
        return recoveryDto;
    }

    @Override
    public TelegramCodeDto getCode(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return TelegramCodeDto.builder()
                .code(user.getTelegramCode())
                .build();

    }
}
