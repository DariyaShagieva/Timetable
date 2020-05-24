package ru.itis.timetable.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatNoteForm {

    private String description;

    private ScheduleForm shedule;




}
