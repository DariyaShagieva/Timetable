package ru.itis.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.timetable.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {


}
