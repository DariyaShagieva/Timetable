package ru.itis.timetable.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.timetable.form.CreatNoteForm;
import ru.itis.timetable.repository.NoteRepository;


@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public void creatNote(CreatNoteForm creatNoteForm) {

    }
}
