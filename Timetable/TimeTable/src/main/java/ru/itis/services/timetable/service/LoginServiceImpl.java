package ru.itis.services.timetable.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.services.timetable.dto.TokenDto;
import ru.itis.services.timetable.dto.UserDto;
import ru.itis.services.timetable.form.LoginForm;
import ru.itis.services.timetable.model.user.User;
import ru.itis.services.timetable.repository.UserRepository;
import ru.itis.services.timetable.security.details.UserDetailsImpl;


import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.prefix}")
    private String prefix;

    private String getTokenAsString(User user) {
        return prefix + " " + Jwts.builder()
                .claim("role", user.getUserRole().toString())
                .claim("email", user.getEmail())
                .setSubject(user.getId().toString())
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }



    @Override
    public UserDto login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail()).orElseThrow(EntityNotFoundException::new);

        if(passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
            TokenDto token =  TokenDto.builder().token(getTokenAsString(user)).build();
            System.out.println(token.getToken());
            return new UserDto(token, user.getEmail(), user.getFirstName());

        } else {
            throw new BadCredentialsException("Incorrect login/password");
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl) authentication.getDetails()).getUser().getId();
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
