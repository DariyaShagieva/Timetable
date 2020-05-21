package ru.itis.timetable.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.timetable.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findById(Long id);

  boolean existsByEmail(String email);


}
